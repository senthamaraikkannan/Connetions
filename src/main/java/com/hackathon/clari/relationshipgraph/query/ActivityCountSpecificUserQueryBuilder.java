package com.hackathon.clari.relationshipgraph.query;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:42 AM
 */
public class ActivityCountSpecificUserQueryBuilder extends CypherQueryBuilder {

    public static String getActivityCountCypher(final String userEmail) {
        return "MATCH (i:Internal_User)\n" +
                "WHERE i.email='" + userEmail + "'\n" +
                "MATCH (activity)-[*..1]->(i)\n" +
                "WITH DISTINCT i.email as email ,i.name AS userName, count( DISTINCT (activity)) AS count, labels(activity) AS label\n" +
                "return userName, collect(apoc.map.fromValues([label, count])) AS sharedActivityCounts, email";
    }
}
