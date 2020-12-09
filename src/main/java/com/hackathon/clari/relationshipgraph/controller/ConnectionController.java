package com.hackathon.clari.relationshipgraph.controller;

import com.hackathon.clari.relationshipgraph.queryresponse.UserAndActivityCountMap;
import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import com.hackathon.clari.relationshipgraph.service.HackathonRelationshipInsightsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Author: senthamaraik kannan
 * Date: 08/12/20
 * Time: 6:06 PM
 */
@RestController
public class ConnectionController {

    @GetMapping("/graph")
    public List<UserAndActivityCountMap> getTopConnection(@RequestParam("isInternal") final boolean isInternal,
                                                          @RequestParam("email") final String email,
                                                          @RequestParam("limit") final int limit) {

        final CypherQueryParam cypherQueryParam = new CypherQueryParam(email, limit, isInternal);
        final HackathonRelationshipInsightsService hackathonRelationshipInsightsService = new HackathonRelationshipInsightsService(cypherQueryParam);
        return hackathonRelationshipInsightsService.apply(cypherQueryParam);
    }

    @GetMapping("/hello")
    public String getString() {

        return "hello";
    }

}
