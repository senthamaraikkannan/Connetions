package com.hackathon.clari.relationshipgraph.service;

import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import org.junit.jupiter.api.Test;

class TopConnectionServiceTest {

    @Test
    public void connectionTest() {
        final CypherQueryParam cypherQueryParam = new CypherQueryParam("user_8@internaldomain.com", 2, true);
        final CypherQueryParam cypherQueryParam1 = new CypherQueryParam("user_8@internaldomain.com", 2, false);
        final TopConnectionService service = new TopConnectionService();
        service.apply(cypherQueryParam);
        service.apply(cypherQueryParam1);
        System.exit(0);
    }

}