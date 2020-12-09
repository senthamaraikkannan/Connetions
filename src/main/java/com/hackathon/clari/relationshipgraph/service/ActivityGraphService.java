package com.hackathon.clari.relationshipgraph.service;

import com.google.common.base.Stopwatch;
import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import com.hackathon.clari.relationshipgraph.queryParam.QueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.Response;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class ActivityGraphService<P extends QueryParam, R extends Response> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityGraphService.class);

    abstract List<R> apply(final P cypherQueryParam);


    Result executeQuery(final Session session,
                        final String internalConnectionQuery) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        final Result result = session.run(internalConnectionQuery);
        stopwatch.stop();
        LOGGER.info("timeTaken  for cypher :{} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return result;
    }
}
