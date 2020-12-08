package com.hackathon.clari.relationshipgraph.queryParam;

/**
 * Author: senthamaraik kannan
 * Date: 08/12/20
 * Time: 11:48 AM
 */
public class CypherQueryParam {

    private final String email;
    private final int limit;
    private final boolean isInternal;

    public CypherQueryParam(final String email,
                            final int limit,
                            final boolean isInternal) {
        this.email = email;
        this.limit = limit;
        this.isInternal = isInternal;
    }

    public String getEmail() {
        return email;
    }

    public int getLimit() {
        return limit;
    }

    public boolean getIsInternal() {
        return isInternal;
    }
}
