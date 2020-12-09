package com.hackathon.clari.relationshipgraph.resultHandler;

import com.hackathon.clari.relationshipgraph.queryParam.DetailsQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.UserAndActivityCount;
import org.neo4j.driver.Result;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hackathon.clari.relationshipgraph.common.Constants.EMAIL;
import static com.hackathon.clari.relationshipgraph.common.Constants.SHARED_ACTIVITY_COUNTS;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:54 AM
 */
public class ActivityCountSpecificUserQueryResultHandler extends QueryResultHandler<DetailsQueryParam, UserAndActivityCount> {

    public List<UserAndActivityCount> getResults(final Result result, final DetailsQueryParam detailsQueryParam) {
        final List<UserAndActivityCount> userActivityCountList = new ArrayList();
        while (result.hasNext()) {
            final Map<String, Object> resultMap = result.next().asMap();
            final String email = String.valueOf(resultMap.get(EMAIL));
            final List sharedActivityCounts = (List) resultMap.get(SHARED_ACTIVITY_COUNTS);
            final Map<String, Object> activityMap = new HashMap<>();

            sharedActivityCounts.stream().forEach(o -> activityMap.putAll((Map<String, Object>) o));

            final UserAndActivityCount userAndActivityCount = new UserAndActivityCount(String.valueOf(resultMap.get("userName")),
                    activityMap,
                    email,
                    getActivityScore(activityMap));

            userActivityCountList.add(userAndActivityCount);
        }

        return userActivityCountList.stream()
                .sorted(Comparator.comparing(UserAndActivityCount::getActivityScore)
                        .reversed()
                        .thenComparing(UserAndActivityCount::getUserName))
                .collect(Collectors.toList());
    }
}
