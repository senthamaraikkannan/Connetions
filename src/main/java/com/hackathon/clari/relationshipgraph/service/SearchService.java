package com.hackathon.clari.relationshipgraph.service;

import com.hackathon.clari.relationshipgraph.driver.GraphDriver;
import com.hackathon.clari.relationshipgraph.query.ActivityCountSpecificUserQueryBuilder;
import com.hackathon.clari.relationshipgraph.query.UserSearchQueryBuilder;
import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import com.hackathon.clari.relationshipgraph.queryParam.SearchQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.ConnectionsResponse;
import com.hackathon.clari.relationshipgraph.queryresponse.SearchResponse;
import com.hackathon.clari.relationshipgraph.resultHandler.ActivityCountSpecificUserQueryResultHandler;
import com.hackathon.clari.relationshipgraph.resultHandler.SearchQueryResultHandler;
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
public class SearchService extends ActivityGraphService<SearchQueryParam, List<SearchResponse>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    public List<SearchResponse> apply(final SearchQueryParam searchQueryParam) {

        try (final Session session = GraphDriver.getSession()) {
            final String internalConnectionQuery = UserSearchQueryBuilder.getUserSearched(searchQueryParam.getEmail(), true);
            Result result = executeQuery(session, internalConnectionQuery);
            return new SearchQueryResultHandler()
                    .getResults(result, searchQueryParam);
        } catch (Exception e) {
            LOGGER.info("Exception:{}", e);
            return Collections.EMPTY_LIST;
        }
    }
}
