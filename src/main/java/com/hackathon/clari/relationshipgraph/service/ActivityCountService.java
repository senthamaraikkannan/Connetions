package com.hackathon.clari.relationshipgraph.service;

import com.hackathon.clari.relationshipgraph.driver.GraphDriver;
import com.hackathon.clari.relationshipgraph.query.ActivityCountQueryBuilder;
import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.ConnectionsResponse;
import com.hackathon.clari.relationshipgraph.queryresponse.UserAndActivityCount;
import com.hackathon.clari.relationshipgraph.resultHandler.ActivityCountQueryResultHandler;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:47 AM
 */
public class ActivityCountService extends ActivityGraphService<CypherQueryParam, List<UserAndActivityCount>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityCountService.class);



    public List<UserAndActivityCount> apply(final CypherQueryParam cypherQueryParam) {

        try (final Session session = GraphDriver.getSession()) {
            final boolean isInternal = cypherQueryParam.getIsInternal();
            final String internalConnectionQuery = ActivityCountQueryBuilder.getActivityCountCypher(cypherQueryParam.getEmail(), isInternal);
            Result result = executeQuery(session, internalConnectionQuery);
            return new ActivityCountQueryResultHandler()
                    .getResults(result,cypherQueryParam);
        } catch (Exception e) {
            LOGGER.info("Exception:{}", e);
            return Collections.EMPTY_LIST;
        }
    }
}
