package com.hackathon.clari.relationshipgraph.service;

import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.ConnectionsResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

class TopConnectionServiceTest {

    @Test
    public void connectionTest() {
        final CypherQueryParam cypherQueryParam = new CypherQueryParam("sgordon@purestorage.com", 2, true);
        final CypherQueryParam cypherQueryParam1 = new CypherQueryParam("sgordon@purestorage.com", 2, false);
        final TopConnectionService service = new TopConnectionService();
        final List<ConnectionsResponse> apply = service.apply(cypherQueryParam);
        final List<ConnectionsResponse> apply1 = service.apply(cypherQueryParam1);
        System.exit(0);
    }

}