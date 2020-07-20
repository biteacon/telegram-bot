package com.biteacon.services;

import com.biteacon.BotConfig;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SearchService {
    private HttpClient client;
    private Duration duration;

    public static SearchService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private SearchService(){
        client = HttpClient.newHttpClient();
        duration = Duration.ofMinutes(1);
    }

    public String search(String key) {
        try {
            return getAccountByAddress(key);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "404";
    }

    private String getAccountByAddress(String address) throws IOException, InterruptedException {
        return executeQuery(getRequestBody(getAccountByAddressRequest(address)));
    }

    private String getAccountByAddressRequest(String address) {
        return "query MyQuery {\n" +
                "  likelib_accounts(where: {address: {_eq: \\\"" + address + "\\\"}}) {\n" +
                "    address\n" +
                "    balance\n" +
                "    nonce\n" +
                "    type\n" +
                "    transactions {\n" +
                "      hash\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
    }

    private String getRequestBody(String request) {
        return "{ \"query\": \"" + request + "\" }";
    }

    private String executeQuery(String requestBody) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BotConfig.GRAPHQL_URL))
                .timeout(duration)
                .headers(BotConfig.GRAPHQL_HEADERS)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<?> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body().toString();
    }


    private static class SingletonHolder {
        private static SearchService INSTANCE = new SearchService();
    }
}