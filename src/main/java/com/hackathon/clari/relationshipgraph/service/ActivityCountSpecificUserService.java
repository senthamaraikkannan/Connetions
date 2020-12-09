package com.hackathon.clari.relationshipgraph.service;

import com.hackathon.clari.relationshipgraph.driver.GraphDriver;
import com.hackathon.clari.relationshipgraph.query.ActivityCountSpecificUserQueryBuilder;
import com.hackathon.clari.relationshipgraph.queryParam.DetailsQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.UserAndActivityCount;
import com.hackathon.clari.relationshipgraph.resultHandler.ActivityCountSpecificUserQueryResultHandler;
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
public class ActivityCountSpecificUserService extends ActivityGraphService<DetailsQueryParam, UserAndActivityCount> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityCountSpecificUserService.class);


    public List<UserAndActivityCount> apply(final DetailsQueryParam detailsQueryParam) {

        try (final Session session = GraphDriver.getSession()) {
            final String internalConnectionQuery = ActivityCountSpecificUserQueryBuilder.getActivityCountCypher(detailsQueryParam.getEmail());
            Result result = executeQuery(session, internalConnectionQuery);
            return new ActivityCountSpecificUserQueryResultHandler()
                    .getResults(result, detailsQueryParam);
        } catch (Exception e) {
            LOGGER.info("Exception:{}", e);
            return Collections.EMPTY_LIST;
        }
    }
}
