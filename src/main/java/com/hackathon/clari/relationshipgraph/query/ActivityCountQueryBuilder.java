package com.hackathon.clari.relationshipgraph.query;

import static com.hackathon.clari.relationshipgraph.query.Common.EXTERNAL_USER;
import static com.hackathon.clari.relationshipgraph.query.Common.INTERNAL_USER;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:42 AM
 */
public class ActivityCountQueryBuilder extends CypherQueryBuilder {

    public static String getActivityCountCypher(final String userEmail,
                                                final Boolean isInternal) {

        final String user = isInternal ? INTERNAL_USER :
                EXTERNAL_USER;

        return "MATCH (i:External_User)\n" +
                "WHERE i.email='" + userEmail + "'\n" +
                "MATCH (user)<-[*..1]-(activity)-[*..1]->(i)\n" +
                "WHERE user:" + user + "\n" +
                "WITH DISTINCT user.email as email ,user.name AS userName, count( DISTINCT (activity)) AS count, labels(activity) AS label\n" +
                "return userName, collect(apoc.map.fromValues([label, count])) AS sharedActivityCounts, email";
    }
}
