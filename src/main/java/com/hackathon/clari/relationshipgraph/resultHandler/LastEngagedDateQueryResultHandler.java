package com.hackathon.clari.relationshipgraph.resultHandler;

import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.UserAndLastEngaged;
import org.neo4j.driver.Result;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hackathon.clari.relationshipgraph.common.Constants.EMAIL;
import static com.hackathon.clari.relationshipgraph.common.Constants.LAST_ENGAGED_DATE;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:54 AM
 */
public class LastEngagedDateQueryResultHandler extends QueryResultHandler<CypherQueryParam, UserAndLastEngaged> {


    public List<UserAndLastEngaged> getResults(final Result result, final CypherQueryParam cypherQueryParam) {
        final List<UserAndLastEngaged> userAndLastEngagedList = new ArrayList();
        while (result.hasNext()) {
            final Map<String, Object> resultMap = result.next().asMap();
            final String email = String.valueOf(resultMap.get(EMAIL));
            final ZonedDateTime lastEngagedDate = (ZonedDateTime) resultMap.get(LAST_ENGAGED_DATE);
            final UserAndLastEngaged userAndLastEngaged = new UserAndLastEngaged(email, lastEngagedDate.toInstant().toEpochMilli());
            userAndLastEngagedList.add(userAndLastEngaged);
        }

        return userAndLastEngagedList;
    }
}
