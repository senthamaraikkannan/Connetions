package com.hackathon.clari.relationshipgraph.query;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:42 AM
 */
public class MagicPathQueryBuilder extends CypherQueryBuilder {

    public static String getMagicPath(final String userEmail,
                                      final String text,
                                      final boolean isAccountSearch) {
        if (isAccountSearch) {
            return "MATCH (i:Internal_User)\n" +
                    "WHERE i.email='" + userEmail + "'\n" +
                    "MATCH p = (a:Account)-[]-(externalUser:External_User)<-[]-(activity2)-[]-(internalUser:Internal_User)<-[]-(activity1)-[]->(i)\n" +
                    "where a.name=~'(?i).*" + text + ".*'\n" +
                    "return  distinct externalUser.email as email, externalUser.name as userName,min(p) as path,max(activity2.date) as lastEngagedDate\n" +
                    "order by externalUser.name asc";
        }
        return "MATCH (i:Internal_User)\n" +
                "WHERE i.email='" + userEmail + "'\n" +
                "MATCH p = (a:Account)-[]-(externalUser:External_User)<-[]-(activity2)-[]-(internalUser:Internal_User)<-[]-(activity1)-[]->(i)\n" +
                "where externalUser.name=~'(?i).*" + text + ".*'\n" +
                "return  distinct externalUser.email as email, externalUser.name as userName,min(p) as path,max(activity2.date) as lastEngagedDate\n" +
                "order by externalUser.name asc";
    }

    public static String getFirstDegreeMagicPath(final String userEmail,
                                      final String text,
                                      final boolean isAccountSearch) {
        if (isAccountSearch) {
            return "MATCH (i:Internal_User)\n" +
                    "WHERE i.email='" + userEmail + "'\n" +
                    "MATCH p = (a:Account)-[]-(externalUser:External_User)<-[]-(activity1)-[]->(i)\n" +
                    "where a.name=~'(?i).*" + text + ".*'\n" +
                    "return  distinct externalUser.email as email, externalUser.name as userName,min(p) as path,max(activity1.date) as lastEngagedDate\n" +
                    "order by externalUser.name asc";
        }
        return "MATCH (i:Internal_User)\n" +
                "WHERE i.email='" + userEmail + "'\n" +
                "MATCH p = (a:Account)-[]-(externalUser:External_User)<-[]-(activity1)-[]->(i)\n" +
                "where externalUser.name=~'(?i).*" + text + ".*'\n" +
                "return  distinct externalUser.email as email, externalUser.name as userName,min(p) as path,max(activity1.date) as lastEngagedDate\n" +
                "order by externalUser.name asc";
    }
}
