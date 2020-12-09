package com.hackathon.clari.relationshipgraph.query;

import static com.hackathon.clari.relationshipgraph.query.Common.EXTERNAL_USER;
import static com.hackathon.clari.relationshipgraph.query.Common.INTERNAL_USER;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:43 AM
 */
public class UserSearchQueryBuilder extends CypherQueryBuilder {

    public static String getUserSearched(final String text,
                                         final Boolean isInternal) {

        final String user = isInternal ? INTERNAL_USER :
                EXTERNAL_USER;

        return "MATCH (i)\n" +
                "WHERE i:" + user + "  AND i.email =~ '.*" + text + ".*'\n" +
                "WITH Distinct i\n" +
                "return i.email as email";
    }
}
