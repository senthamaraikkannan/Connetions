package com.hackathon.clari.relationshipgraph.service;

import com.hackathon.clari.relationshipgraph.queryParam.MagicPathQueryParam;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

class MagicPathServiceTest {


    @Test
    public void magicPathTest() {
        final MagicPathQueryParam cypherQueryParam = new MagicPathQueryParam("will@clari.com", "Chip Cookston", false);
        final MagicPathService service = new MagicPathService();
        service.apply(cypherQueryParam);
        System.exit(0);
    }

    @Test
    public void magicPathTest2() {
        final MagicPathQueryParam cypherQueryParam = new MagicPathQueryParam("will@clari.com", "Anthony Cerche", false);
        final MagicPathService service = new MagicPathService();
        service.apply(cypherQueryParam);
        System.exit(0);
    }

    @Test
    public void percentileTest() {
        final Map<Integer, Integer> integerIntegerMap = ActivityGraphService.calculatePercentiles(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        integerIntegerMap.size();
    }


}