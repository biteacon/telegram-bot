package com.biteacon.constants;

public class BotConfig {
    public static final String EXPLORER_TOKEN = "<token>";
    public static final String EXPLORER_USER = "Biteacon_bot";
    public static final String GRAPHQL_URL = "http://localhost:8081/v1/graphql";
    public static final String[] GRAPHQL_HEADERS = {"Content-type", "application/json", "Accept", "text/plain", "x-hasura-admin-secret", "changeme2"};

    private BotConfig() {}
}
