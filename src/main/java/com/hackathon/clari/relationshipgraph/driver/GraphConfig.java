package com.hackathon.clari.relationshipgraph.driver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GraphConfig {

    public static final String HOST_NAME = "hostName";
    public static final String PORT = "port";
    public static final String USER_NAME = "userName";
    public static final String PASSWORD = "password";
    public static final String DRIVER = "driver";

    @JsonProperty(DRIVER)
    private final String driver;

    @JsonProperty(HOST_NAME)
    private final String hostName;

    @JsonProperty(PORT)
    private final int port;

    @JsonProperty(USER_NAME)
    private final String userName;

    @JsonProperty(PASSWORD)
    private final String password;

    @JsonCreator
    public GraphConfig(@JsonProperty(DRIVER) final String driver,
                       @JsonProperty(HOST_NAME) final String hostName,
                       @JsonProperty(PORT) final int port,
                       @JsonProperty(USER_NAME) final String userName,
                       @JsonProperty(PASSWORD) final String password) {
        this.driver = driver;
        this.hostName = hostName;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
