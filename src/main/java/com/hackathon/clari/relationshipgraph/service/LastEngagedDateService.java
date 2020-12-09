package com.hackathon.clari.relationshipgraph.service;

import com.hackathon.clari.relationshipgraph.driver.GraphDriver;
import com.hackathon.clari.relationshipgraph.query.LastEngagedDateQueryBuilder;
import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.UserAndLastEngaged;
import com.hackathon.clari.relationshipgraph.resultHandler.LastEngagedDateQueryResultHandler;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:47 AM
 */
public class LastEngagedDateService extends ActivityGraphService<CypherQueryParam, UserAndLastEngaged> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LastEngagedDateService.class);
    private final List<String> userEmailList;

    public LastEngagedDateService(final List<String> userEmailList) {
        this.userEmailList = userEmailList;
    }

    public List<UserAndLastEngaged> apply(final CypherQueryParam cypherQueryParam) {
        try (final Session session = GraphDriver.getSession()) {
            final boolean isInternal = cypherQueryParam.getIsInternal();
            final String lastEngagedCypherQuery = LastEngagedDateQueryBuilder.getLastEngagedCypherQuery(userEmailList, isInternal);
            final Result result = executeQuery(session, lastEngagedCypherQuery);
            return new LastEngagedDateQueryResultHandler()
                    .getResults(result, cypherQueryParam)
                    .stream()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.info("Exception:{}", e);
            return Collections.EMPTY_LIST;
        }
    }
}
