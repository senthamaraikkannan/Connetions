package com.hackathon.clari.relationshipgraph.queryresponse;

import java.util.Map;

/**
 * Author: senthamaraik kannan
 * Date: 08/12/20
 * Time: 9:02 AM
 */
public class UserAndActivityCountMap {

    private final String userName;
    private final Map<String, Object> activityCountMap;
    private final String userEmail;
    private final Long activityScore;


    public UserAndActivityCountMap(final String userName,
                                   final Map<String, Object> activityCountMap,
                                   final String userEmail,
                                   final Long activityScore) {
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

    public Long getActivityScore() {
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
