package com.hackathon.clari.relationshipgraph.queryresponse;

/**
 * Author: senthamaraik kannan
 * Date: 08/12/20
 * Time: 9:02 AM
 */
public class SearchResponse extends Response {

    private final String userEmail;

    public SearchResponse(final String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
