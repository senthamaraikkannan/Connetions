package com.hackathon.clari.relationshipgraph.queryParam;

/**
 * Author: senthamaraik kannan
 * Date: 08/12/20
 * Time: 11:48 AM
 */
public class MagicPathQueryParam implements QueryParam {

    private final String email;
    private final String searchText;
    private final boolean isAccountSearch;

    public MagicPathQueryParam(final String email,
                               final String searchText,
                               final boolean isAccountSearch) {
        this.email = email;
        this.searchText = searchText;
        this.isAccountSearch = isAccountSearch;
    }

    public String getEmail() {
        return email;
    }

    public String getSearchText() {
        return searchText;
    }

    public boolean getIsAccountSearch() {
        return isAccountSearch;
    }
}
