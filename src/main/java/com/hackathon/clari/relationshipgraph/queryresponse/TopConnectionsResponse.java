package com.hackathon.clari.relationshipgraph.queryresponse;

import java.util.List;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 8:48 AM
 */
public class TopConnectionsResponse extends Response {

    private final List<ConnectionsResponse> connectionsResponseList;

    public TopConnectionsResponse(final List<ConnectionsResponse> connectionsResponseList) {
        this.connectionsResponseList = connectionsResponseList;
    }

    public List<ConnectionsResponse> getConnectionsResponseList() {
        return connectionsResponseList;
    }
}
