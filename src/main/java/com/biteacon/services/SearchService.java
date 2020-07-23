package com.biteacon.services;

import com.biteacon.constants.BotConfig;
import com.biteacon.exceptions.SearchException;

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

    public HttpResponse<?> getBlockByHash(String hash) throws SearchException {
        return executeQuery(getRequestBody(getBlockByHashRequest(hash)));
    }

    private String getBlockByHashRequest(String hash) {
        return "query MyQuery {\n" +
                "  likelib_blocks(where: {_or: [{hash: {_eq: \\\"" + hash + "\\\"}}," +
                "{hash: {_eq: \\\"" + hash.substring(1) + "\\\"}}," +
                "{hash: {_ilike: \\\"" + hash.substring(1) + "%\\\"}}," +
                "{hash: {_ilike: \\\"" + hash + "%\\\"}}]}) {\n" +
                "    hash\n" +
                "    height\n" +
                "    prev_block_hash\n" +
                "    nonce\n" +
                "    coinbase\n" +
                "    timestamp\n" +
                "    transactions {\n" +
                "      hash\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
    }

    public HttpResponse<?> getBlockByHeight(String height) throws SearchException {
        return executeQuery(getRequestBody(getBlockByHeightRequest(height)));
    }

    private String getBlockByHeightRequest(String height) {
        return "query MyQuery {\n" +
                "  likelib_blocks(where: {height: {_eq: \\\"" + height + "\\\"}}) {\n" +
                "    hash\n" +
                "    height\n" +
                "    prev_block_hash\n" +
                "    coinbase\n" +
                "    nonce\n" +
                "    timestamp\n" +
                "    transactions {\n" +
                "      hash\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
    }

    public HttpResponse<?> getTransactionByHash(String hash) throws SearchException {
        return executeQuery(getRequestBody(getTransactionByHashRequest(hash)));
    }

    private String getTransactionByHashRequest(String hash) {
        return "query MyQuery {\n" +
                "  likelib_transactions(where: {_or: [{hash: {_eq: \\\"" + hash + "\\\"}}," +
                "{hash: {_eq: \\\"" + hash.substring(1) + "\\\"}}," +
                "{hash: {_ilike: \\\"" + hash.substring(1) + "%\\\"}}," +
                "{hash: {_ilike: \\\"" + hash + "%\\\"}}]}) {\n" +
                "    hash\n" +
                "    block_height\n" +
                "    amount\n" +
                "    account_from\n" +
                "    account_to\n" +
                "    fee\n" +
                "    fee_left\n" +
                "    status\n" +
                "    timestamp\n" +
                "    type\n" +
                "    message\n" +
                "    sign\n" +
                "    data\n" +
                "  }\n" +
                "}\n";
    }

    public HttpResponse<?> getAccountByAddress(String address) throws SearchException {
        return executeQuery(getRequestBody(getAccountByAddressRequest(address)));
    }

    private String getAccountByAddressRequest(String address) {
        return "query MyQuery {\n" +
                "  likelib_accounts(where: {_or: [ {address: {_eq: \\\"" + address +
                "\\\"}}, {address: {_eq: \\\"" + address.substring(1) + "\\\"}}]}) {\n" +
                "    address\n" +
                "    balance\n" +
                "    nonce\n" +
                "    type\n" +
                "    transactions {\n" +
                "      hash\n" +
                "    }\n" +
                "    transactionsByAccountTo {\n" +
                "      hash\n" +
                "    }" +
                "    blocks {\n" +
                "      height\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
    }

    private String getRequestBody(String request) {
        return "{ \"query\": \"" + request + "\" }";
    }

    private HttpResponse<?> executeQuery(String requestBody) throws SearchException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BotConfig.GRAPHQL_URL))
                .timeout(duration)
                .headers(BotConfig.GRAPHQL_HEADERS)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<?> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new SearchException(e);
        }
        return response;
    }


    private static class SingletonHolder {
        private static SearchService INSTANCE = new SearchService();
    }
}
