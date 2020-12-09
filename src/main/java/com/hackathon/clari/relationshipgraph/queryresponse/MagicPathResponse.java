package com.hackathon.clari.relationshipgraph.queryresponse;

import com.hackathon.clari.relationshipgraph.resultHandler.MagicPathNode;
import com.hackathon.clari.relationshipgraph.resultHandler.MagicPathRelationship;

import java.util.List;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 8:48 AM
 */
public class MagicPathResponse extends Response {

    private final String userName;
    private final String userEmail;
    private final Long lasEngagedDate;
    private final List<MagicPathNode> nodeList;
    private final List<MagicPathRelationship> relationshipList;

    public MagicPathResponse(final String userName,
                             final String userEmail,
                             final Long lasEngagedDate,
                             final List<MagicPathNode> nodeList,
                             final List<MagicPathRelationship> relationshipList) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.lasEngagedDate = lasEngagedDate;
        this.nodeList = nodeList;
        this.relationshipList = relationshipList;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getLasEngagedDate() {
        return lasEngagedDate;
    }

    public List<MagicPathNode> getNodeList() {
        return nodeList;
    }

    public List<MagicPathRelationship> getRelationshipList() {
        return relationshipList;
    }
}
