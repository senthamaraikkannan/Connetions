package com.hackathon.clari.relationshipgraph.query;

import java.util.List;
import java.util.stream.Collectors;

import static com.hackathon.clari.relationshipgraph.query.Common.EXTERNAL_USER;
import static com.hackathon.clari.relationshipgraph.query.Common.INTERNAL_USER;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:43 AM
 */
public class LastEngagedDateQueryBuilder extends CypherQueryBuilder {

    public static String getLastEngagedCypherQuery(final List<String> userEmailList,
                                                   final Boolean isInternal) {

        final String user = isInternal ? INTERNAL_USER :
                EXTERNAL_USER;

        final String userEmails = userEmailList.stream().map(s -> "'" + s + "'").collect(Collectors.joining(","));

        return "MATCH (i)\n" +
                "WHERE i:" + user + " AND i.email IN  [ " + userEmails + " ]\n" +
                "MATCH (activity)-[*..1]->(i)\n" +
                "WITH Distinct activity,i\n" +
                "return i.email as email ,max(activity.date) as lastEngagedDate";
    }
}
