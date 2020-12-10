package com.hackathon.clari.relationshipgraph.resultHandler;

import com.hackathon.clari.relationshipgraph.queryParam.QueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.Response;
import org.neo4j.driver.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.hackathon.clari.relationshipgraph.common.Constants.ATTACHMENT_RECEIVED;
import static com.hackathon.clari.relationshipgraph.common.Constants.ATTACHMENT_SENT;
import static com.hackathon.clari.relationshipgraph.common.Constants.EMAIL_RECEIVED;
import static com.hackathon.clari.relationshipgraph.common.Constants.EMAIL_SENT;
import static com.hackathon.clari.relationshipgraph.common.Constants.MEETING;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:54 AM
 */
public abstract class QueryResultHandler<P extends QueryParam, R extends Response> {

    private static final Map<String, Integer> ACTIVITY_VS_WEIGHT = new HashMap() {{
        put(MEETING, 10);
        put(EMAIL_SENT, 3);
        put(EMAIL_RECEIVED, 5);
        put(ATTACHMENT_SENT, 1);
        put(ATTACHMENT_RECEIVED, 2);
    }};

    public abstract List<R> getResults(final Result result, final P queryParam);

    Integer getActivityScore(final Map<String, Object> activityCountMap) {

        if (activityCountMap == null || activityCountMap.isEmpty())
            return 0;


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
        final long sum = ACTIVITY_VS_WEIGHT.values().stream().mapToLong(Long::valueOf).sum();
        final int ceil = (int) Math.ceil(activityScore.get() / sum);
        return ceil;
    }

}
