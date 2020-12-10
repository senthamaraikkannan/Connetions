package com.hackathon.clari.relationshipgraph.service;

import com.google.common.base.Stopwatch;
import com.hackathon.clari.relationshipgraph.queryParam.QueryParam;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class ActivityGraphService<P extends QueryParam, R> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityGraphService.class);

    abstract R apply(final P cypherQueryParam);


    Result executeQuery(final Session session,
                        final String internalConnectionQuery) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        final Result result = session.run(internalConnectionQuery);
        stopwatch.stop();
        LOGGER.info("timeTaken  for cypher :{} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return result;
    }

    public static Map<Integer, Integer> calculatePercentiles(final List<Integer> integerList) {
        Collections.sort(integerList);
        final Map<Integer, Integer> activityScorePercentileMap = new HashMap<>();
        for (int i = 0; i < integerList.size(); i++) {
            int count = 0;
            int start = i;
            if (i > 0) {
                while (i > 0 && integerList.get(i) == integerList.get(i - 1)) {
                    count++;
                    i++;
                }
            }
            double percentile = ((start - 0) + (0.5 * count));
            percentile = percentile / (integerList.size() - 1);
            for (int k = 0; k < count + 1; k++)
                activityScorePercentileMap.put(integerList.get(start + k), (int) (percentile * 100));
        }
        return activityScorePercentileMap;
    }
}
