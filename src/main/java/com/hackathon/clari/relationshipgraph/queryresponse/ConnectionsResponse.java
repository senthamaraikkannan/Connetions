package com.hackathon.clari.relationshipgraph.queryresponse;

import java.util.Map;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 8:48 AM
 */
public class ConnectionsResponse extends Response {

    private final String userName;
    private final Map<String, Object> activityCountMap;
    private final String userEmail;
    private final Integer activityScore;
    private final Long lasEngagedDate;

    public ConnectionsResponse(final String userName,
                               final Map<String, Object> activityCountMap,
                               final String userEmail,
                               final Integer activityScore,
                               final Long lasEngagedDate) {
        this.userName = userName;
        this.activityCountMap = activityCountMap;
        this.userEmail = userEmail;
        this.activityScore = activityScore;
        this.lasEngagedDate = lasEngagedDate;
    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Object> getActivityCountMap() {
        return activityCountMap;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Integer getActivityScore() {
        return activityScore;
    }

    public Long getLasEngagedDate() {
        return lasEngagedDate;
    }
}
