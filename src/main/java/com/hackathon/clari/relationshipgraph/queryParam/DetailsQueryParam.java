package com.hackathon.clari.relationshipgraph.queryParam;

/**
 * Author: senthamaraik kannan
 * Date: 08/12/20
 * Time: 11:48 AM
 */
public class DetailsQueryParam implements QueryParam {

    private final String email;
    private final Boolean isInternal;

    public DetailsQueryParam(final String email, final Boolean isInternal) {
        this.email = email;
        this.isInternal = isInternal;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getInternal() {
        return isInternal;
    }
}
