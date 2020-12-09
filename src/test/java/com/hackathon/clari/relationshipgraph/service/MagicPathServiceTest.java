package com.hackathon.clari.relationshipgraph.service;

import com.hackathon.clari.relationshipgraph.queryParam.MagicPathQueryParam;
import org.junit.jupiter.api.Test;

class MagicPathServiceTest {

    @Test
    public void magicPathTest() {
        final MagicPathQueryParam cypherQueryParam = new MagicPathQueryParam("will@clari.com", "Chip Cookston", false);
        final MagicPathService service = new MagicPathService();
        service.apply(cypherQueryParam);
        System.exit(0);
    }

}