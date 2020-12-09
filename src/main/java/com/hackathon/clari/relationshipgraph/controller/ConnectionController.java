package com.hackathon.clari.relationshipgraph.controller;

import com.hackathon.clari.relationshipgraph.queryParam.CypherQueryParam;
import com.hackathon.clari.relationshipgraph.queryParam.DetailsQueryParam;
import com.hackathon.clari.relationshipgraph.queryParam.SearchQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.ConnectionsResponse;
import com.hackathon.clari.relationshipgraph.queryresponse.SearchResponse;
import com.hackathon.clari.relationshipgraph.service.DetailsService;
import com.hackathon.clari.relationshipgraph.service.SearchService;
import com.hackathon.clari.relationshipgraph.service.TopConnectionService;
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

    @GetMapping("/top-connection")
    public List<ConnectionsResponse> getTopConnection(@RequestParam("isInternal") final boolean isInternal,
                                                      @RequestParam("email") final String email,
                                                      @RequestParam("limit") final int limit) {

        final CypherQueryParam cypherQueryParam = new CypherQueryParam(email, limit, isInternal);
        final TopConnectionService topConnectionService = new TopConnectionService();
        return topConnectionService.apply(cypherQueryParam);
    }

    @GetMapping("/search")
    public List<SearchResponse> getSearchedUsers(@RequestParam("email") final String email) {

        final SearchQueryParam searchQueryParam = new SearchQueryParam(email);
        final SearchService searchService = new SearchService();
        return searchService.apply(searchQueryParam);
    }

    @GetMapping("/details")
    public List<ConnectionsResponse> getDetails(@RequestParam("email") final String email,
                                                @RequestParam("isInternal") final boolean isInternal) {

        final DetailsQueryParam cypherQueryParam = new DetailsQueryParam(email, isInternal);
        final DetailsService detailsService = new DetailsService();
        return detailsService.apply(cypherQueryParam);
    }

    @GetMapping("/hello")
    public String getString() {

        return "hello";
    }

}
