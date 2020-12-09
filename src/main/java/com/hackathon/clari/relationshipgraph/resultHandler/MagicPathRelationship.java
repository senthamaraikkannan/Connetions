package com.hackathon.clari.relationshipgraph.resultHandler;

import java.util.Map;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 9:16 PM
 */
public class MagicPathRelationship {
    private long start;
    private long end;
    private long id;
    private String type;
    private Map<String, Object> propertiesMap;

    public MagicPathRelationship(final long start,
                                 final long end,
                                 final long id,
                                 final String type,
                                 final Map<String, Object> propertiesMap) {
        this.start = start;
        this.end = end;
        this.id = id;
        this.type = type;
        this.propertiesMap = propertiesMap;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getPropertiesMap() {
        return propertiesMap;
    }
}
