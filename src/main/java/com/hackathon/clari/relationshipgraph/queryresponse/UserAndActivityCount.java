package com.hackathon.clari.relationshipgraph.queryresponse;

import java.util.Map;

/**
 * Author: senthamaraik kannan
 * Date: 08/12/20
 * Time: 9:02 AM
 */
public class UserAndActivityCount extends Response {

    private final String userName;
    private final Map<String, Object> activityCountMap;
    private final String userEmail;
    private final Integer activityScore;


    public UserAndActivityCount(final String userName,
                                final Map<String, Object> activityCountMap,
                                final String userEmail,
                                final Integer activityScore) {
        this.userName = userName;
        this.activityCountMap = activityCountMap;
        this.userEmail = userEmail;
        this.activityScore = activityScore;
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

    @Override
    public String toString() {
        return "UserAndActivityCountMap{" +
                "userName='" + userName + '\'' +
                ", activityCountMap=" + activityCountMap +
                ", userEmail='" + userEmail + '\'' +
                ", activityScore=" + activityScore +
                '}';
    }
}
