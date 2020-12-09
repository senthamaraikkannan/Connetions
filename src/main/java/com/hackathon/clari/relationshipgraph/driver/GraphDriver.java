package com.hackathon.clari.relationshipgraph.driver;


import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;

public class GraphDriver {

    public static final String URL_SEPARATOR = ":";
    public static final String DRIVER_SEPARATOR = URL_SEPARATOR + "//";

    public static Driver getBoltInstance(final GraphConfig propValues) {
        return LazyHolder.getBoltDriver(propValues);
    }

    public static Session getSession() throws Exception {
        final GraphConfig propValues = GraphConfigPropValues.getPropValues();
        return getBoltInstance(propValues).session(SessionConfig.forDatabase(propValues.getDatabaseName()));
    }

    public static Session getSession(final String databaseName) throws Exception {
        final GraphConfig propValues = GraphConfigPropValues.getPropValues();
        return getBoltInstance(propValues).session(SessionConfig.forDatabase(databaseName));
    }

    private static class LazyHolder {
        private static Driver driver;

        private LazyHolder() {
        }

        public static synchronized Driver getBoltDriver(final GraphConfig propValues) {
            if (driver == null) {
                try {
                    final String uri = new StringBuilder()
                            .append(propValues.getDriver())
                            .append(DRIVER_SEPARATOR)
                            .append(propValues.getHostName())
                            .append(URL_SEPARATOR)
                            .append(propValues.getPort())
                            .toString();

                    driver = GraphDatabase.driver(uri, AuthTokens.basic(propValues.getUserName(), propValues.getPassword()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return driver;
            }
            return driver;
        }
    }

}
