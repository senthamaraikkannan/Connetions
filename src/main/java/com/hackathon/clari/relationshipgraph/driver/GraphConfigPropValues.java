package com.hackathon.clari.relationshipgraph.driver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author: senthamaraik kannan
 * Date: 02/07/20
 * Time: 9:54 AM
 */
public class GraphConfigPropValues {

    public static final String UNABLE_TO_FIND_CONFIG_PROPERTIES = "unable to find neo4j.json";
    public static final String CONFIG_PROPERTIES = "neo4j.json";

    public static GraphConfig getPropValues() throws Exception {

        try (final InputStream input = GraphConfigPropValues.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
            if (input == null) {
                throw new Exception(UNABLE_TO_FIND_CONFIG_PROPERTIES);
            }

            //get the property value and print it out
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(input, new TypeReference<GraphConfig>() {
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
