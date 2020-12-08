package com.hackathon.clari.relationshipgraph.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Stopwatch;
import com.hackathon.clari.relationshipgraph.cypherqueryResponse.UserAndActivityCountMap;
import com.hackathon.clari.relationshipgraph.driver.GraphDriver;
import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Hackathon only
 */
public class HackathonRelationshipInsightsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HackathonRelationshipInsightsService.class);
    private static final String EXTERNAL_USER = "External_User";
    private static final String INTERNAL_USER = "Internal_User";

    private static final String EMAIL_RECEIVED = "Email_Received";
    private static final String EMAIL_SENT = "Email_Sent";
    private static final String MEETING = "Meeting";
    private static final String ATTACHMENT_SENT = "Attachment_Sent";
    private static final String ATTACHMENT_RECEIVED = "Attachment_Received";
    private static final Map<String, Integer> ACTIVITY_VS_WEIGHT = new HashMap() {{
        put(MEETING, 10);
        put(EMAIL_SENT, 3);
        put(EMAIL_RECEIVED, 5);
        put(ATTACHMENT_SENT, 1);
        put(ATTACHMENT_RECEIVED, 2);
    }};
    private final CypherQueryParam cypherQueryParam;

    public HackathonRelationshipInsightsService(final CypherQueryParam cypherQueryParam) {

        this.cypherQueryParam = cypherQueryParam;
    }

    public static void main(String args[]) {
        final CypherQueryParam cypherQueryParam = new CypherQueryParam("user_8@internaldomain.com", 2, true);
        final CypherQueryParam cypherQueryParam1 = new CypherQueryParam("user_8@internaldomain.com", 2, false);
        final HackathonRelationshipInsightsService service = new HackathonRelationshipInsightsService(cypherQueryParam);
        service.apply(cypherQueryParam);
        service.apply(cypherQueryParam1);
        System.exit(0);
    }

    public List<UserAndActivityCountMap> apply(final CypherQueryParam cypherQueryParam1) {


        try (final Session session = GraphDriver.getSession("d0000000013")) {
            final String internalConnectionQuery = getActivityCountCypher(cypherQueryParam1.getEmail(), cypherQueryParam1.getIsInternal());
            final List<UserAndActivityCountMap> internalConnections = executeQuery(session, internalConnectionQuery, cypherQueryParam1.getLimit());

            final ObjectMapper objectMapper = new ObjectMapper();

            LOGGER.info(" Connection for isInternal:{} : {}", cypherQueryParam1.getIsInternal(), objectMapper.writeValueAsString(internalConnections));

            return internalConnections;
        } catch (Exception e) {
            LOGGER.info("Exception:{}", e);
            return Collections.EMPTY_LIST;
        }
    }

    private List<UserAndActivityCountMap> executeQuery(final Session session,
                                                       final String internalConnectionQuery,
                                                       final int limit) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        final Result result = session.run(internalConnectionQuery);
        stopwatch.stop();
        LOGGER.info("timeTaken  for cypher :{} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return getResults(result, limit);
    }

    private List<UserAndActivityCountMap> getResults(final Result result,
                                                     final int limit) {
        final List<UserAndActivityCountMap> userActivityCountList = new ArrayList();
        while (result.hasNext()) {
            final Map<String, Object> stringObjectMap = result.next().asMap();
            final String email = String.valueOf(stringObjectMap.get("email"));
            final List sharedActivityCounts = (List) stringObjectMap.get("sharedActivityCounts");
            final Map<String, Object> activityMap = new HashMap<>();

            sharedActivityCounts.stream().forEach(o -> activityMap.putAll((Map<String, Object>) o));

            final UserAndActivityCountMap userAndActivityCountMap = new UserAndActivityCountMap(String.valueOf(stringObjectMap.get("userName")),
                    activityMap,
                    email,
                    getActivityScore(activityMap));

            userActivityCountList.add(userAndActivityCountMap);
        }

        return userActivityCountList.stream()
                .sorted(Comparator.comparing(UserAndActivityCountMap::getActivityScore)
                        .reversed()
                        .thenComparing(UserAndActivityCountMap::getUserName))
                .limit(limit).collect(Collectors.toList());
    }

    private String getSimpleCypher() {
        return "MATCH (i:Internal_User) return i";
    }

    private String getActivityCountCypher(final String userEmail,
                                          final Boolean isInternal) {

        final String user = isInternal ? INTERNAL_USER :
                EXTERNAL_USER;

        return "MATCH (i:Internal_User)\n" +
                "WHERE i.email='" + userEmail + "'\n" +
                "MATCH (user)<-[*..1]-(activity)-[*..1]->(i)\n" +
                "WHERE user:" + user + "\n" +
                "WITH DISTINCT user.email as email ,user.name AS userName, count( DISTINCT (activity)) AS count, labels(activity) AS label\n" +
                "return userName, collect(apoc.map.fromValues([label, count])) AS sharedActivityCounts, email";
    }

    private Long getActivityScore(final Map<String, Object> activityCountMap) {

        if (activityCountMap == null || activityCountMap.isEmpty())
            return Long.valueOf(0);


        final AtomicReference<Long> activityScore = new AtomicReference<>();

        activityCountMap.entrySet().forEach(entry -> {
            String key = entry.getKey();
            key = key.replace("[", "");
            key = key.replace("]", "");
            final long value = (long) entry.getValue();

            final Integer integer = ACTIVITY_VS_WEIGHT.get(key);

            final long newScore = integer * value;
            if (activityScore.get() == null) {
                activityScore.set(newScore);
            } else {
                activityScore.set(activityScore.get() + newScore);
            }
        });
        return activityScore.get();
    }
}
