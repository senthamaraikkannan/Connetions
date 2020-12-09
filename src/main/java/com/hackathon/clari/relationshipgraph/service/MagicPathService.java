package com.hackathon.clari.relationshipgraph.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.clari.relationshipgraph.driver.GraphDriver;
import com.hackathon.clari.relationshipgraph.query.MagicPathQueryBuilder;
import com.hackathon.clari.relationshipgraph.queryParam.MagicPathQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.MagicPathResponse;
import com.hackathon.clari.relationshipgraph.resultHandler.MagicPathQueryResultHandler;
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
public class MagicPathService extends ActivityGraphService<MagicPathQueryParam, List<MagicPathResponse>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MagicPathService.class);


    public List<MagicPathResponse> apply(final MagicPathQueryParam magicPathQueryParam) {

        try (final Session session = GraphDriver.getSession()) {
            final String magicPathQuery = MagicPathQueryBuilder.getMagicPath(magicPathQueryParam.getEmail(), magicPathQueryParam.getSearchText());
            Result result = executeQuery(session, magicPathQuery);
            final List<MagicPathResponse> results = new MagicPathQueryResultHandler()
                    .getResults(result, magicPathQueryParam);
            LOGGER.info("MagicPath" + new ObjectMapper().writeValueAsString(results));
            return results;
        } catch (Exception e) {
            LOGGER.info("Exception:{}", e);
            return Collections.EMPTY_LIST;
        }
    }
}
