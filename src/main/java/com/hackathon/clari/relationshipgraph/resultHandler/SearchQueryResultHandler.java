package com.hackathon.clari.relationshipgraph.resultHandler;

import com.hackathon.clari.relationshipgraph.queryParam.SearchQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.SearchResponse;
import org.neo4j.driver.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hackathon.clari.relationshipgraph.common.Constants.EMAIL;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:54 AM
 */
public class SearchQueryResultHandler extends QueryResultHandler<SearchQueryParam, SearchResponse> {

    public List<SearchResponse> getResults(final Result result, final SearchQueryParam searchQueryParam) {
        final List<SearchResponse> searchResponses = new ArrayList();
        while (result.hasNext()) {
            final Map<String, Object> resultMap = result.next().asMap();
            final String email = String.valueOf(resultMap.get(EMAIL));
            final SearchResponse response = new SearchResponse(email);
            searchResponses.add(response);
        }
        return searchResponses;
    }
}
