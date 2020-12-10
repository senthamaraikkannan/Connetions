package com.hackathon.clari.relationshipgraph.resultHandler;

import java.util.List;
import java.util.Map;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 9:16 PM
 */
public class MagicPathNode {

    private String label;
    private long id;
    private Map<String, Object> propertiesMap;

    public MagicPathNode(final String label, final long id, final Map<String, Object> propertiesMap) {
        this.label = label;
        this.id = id;
        this.propertiesMap = propertiesMap;
    }

    public String getLabel() {
        return label;
    }

    public long getId() {
        return id;
    }

    public Map<String, Object> getPropertiesMap() {
        return propertiesMap;
    }
}
