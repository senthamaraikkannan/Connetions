package com.hackathon.clari.relationshipgraph.driver;


import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;

public class GraphDriver {

    public static Driver getBoltInstance() {
        return LazyHolder.getBoltDriver();
    }

    public static Session getSession() {
        return getBoltInstance().session();
    }

    public static Session getSession(final String databaseName) {
        return getBoltInstance().session(SessionConfig.forDatabase(databaseName));
    }

    private static class LazyHolder {
        private static Driver driver;

        private LazyHolder() {
        }

        public static synchronized Driver getBoltDriver() {
            if (driver == null) {
                driver = GraphDatabase.driver("bolt://192.168.29.31:7687", AuthTokens.basic("neo4j", "Clarius01!"));
            }
            return driver;
        }
    }

}
