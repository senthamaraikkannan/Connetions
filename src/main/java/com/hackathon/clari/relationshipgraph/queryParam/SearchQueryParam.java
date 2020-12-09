package com.hackathon.clari.relationshipgraph.queryParam;

/**
 * Author: senthamaraik kannan
 * Date: 08/12/20
 * Time: 11:48 AM
 */
public class SearchQueryParam implements QueryParam {

    private final String email;

    public SearchQueryParam(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
