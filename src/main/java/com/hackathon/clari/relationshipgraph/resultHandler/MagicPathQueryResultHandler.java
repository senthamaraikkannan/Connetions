package com.hackathon.clari.relationshipgraph.resultHandler;

import com.hackathon.clari.relationshipgraph.common.Constants;
import com.hackathon.clari.relationshipgraph.queryParam.MagicPathQueryParam;
import com.hackathon.clari.relationshipgraph.queryresponse.MagicPathResponse;
import org.neo4j.driver.Result;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.hackathon.clari.relationshipgraph.common.Constants.EMAIL;
import static com.hackathon.clari.relationshipgraph.common.Constants.LAST_ENGAGED_DATE;
import static com.hackathon.clari.relationshipgraph.common.Constants.USER_NAME;

/**
 * Author: senthamaraik kannan
 * Date: 09/12/20
 * Time: 11:54 AM
 */
public class MagicPathQueryResultHandler extends QueryResultHandler<MagicPathQueryParam, MagicPathResponse> {

    public List<MagicPathResponse> getResults(final Result result,
                                              final MagicPathQueryParam magicPathQueryParam) {
        final List<MagicPathResponse> magicPathResponseList = new ArrayList();
        while (result.hasNext()) {
            final Map<String, Object> resultMap = result.next().asMap();
            final String email = String.valueOf(resultMap.get(EMAIL));
            final String userName = String.valueOf(resultMap.get(USER_NAME));
            final long lastEngagedDate = ((ZonedDateTime) resultMap.get(LAST_ENGAGED_DATE)).toInstant().toEpochMilli();
            final InternalPath path = (InternalPath) resultMap.get(Constants.PATH);
            final List<MagicPathNode> nodeList = new ArrayList<>();
            StreamSupport
                    .stream(path.nodes().spliterator(), false)
                    .forEach(node -> {
                        final List<String> labels = StreamSupport
                                .stream(node.labels().spliterator(), false)
                                .collect(Collectors.toList());
                        final Map<String, Object> properties = node.asMap();
                        nodeList.add(new MagicPathNode(labels, node.id(), properties));
                    });

            final List<MagicPathRelationship> relationshipList = new ArrayList<>();

            StreamSupport
                    .stream(path.relationships().spliterator(), false)
                    .forEach(node -> {
                        final Map<String, Object> properties = node.asMap();
                        relationshipList.add(new MagicPathRelationship(node.startNodeId(), node.endNodeId(), node.id(), node.type(), properties));
                    });

            magicPathResponseList.add(
                    new MagicPathResponse(email, userName, lastEngagedDate, nodeList, relationshipList)
            );

        }

        return magicPathResponseList;
    }
}
