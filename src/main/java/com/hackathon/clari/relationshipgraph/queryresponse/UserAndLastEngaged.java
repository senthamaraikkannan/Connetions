package com.hackathon.clari.relationshipgraph.queryresponse;

import java.util.Map;

/**
 * Author: senthamaraik kannan
 * Date: 08/12/20
 * Time: 9:02 AM
 */
public class UserAndLastEngaged extends Response {

    private final String userEmail;
    private final Long lasEngagedDate;


    public UserAndLastEngaged(final String userEmail,
                              final Long lasEngagedDate) {
        this.userEmail = userEmail;
        this.lasEngagedDate = lasEngagedDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getLasEngagedDate() {
        return lasEngagedDate;
    }
}
