package com.biteacon.services;

import com.biteacon.constants.BotConfig;
import com.biteacon.constants.ApplicationConstants;
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

    public HttpResponse<?> insertUser(Long userId, String supersetUsername) throws SearchException {
        return executeQuery(getRequestBody(insertUserRequest(String.valueOf(userId), supersetUsername)));
    }

    private String insertUserRequest(String userId, String supersetUsername) {
        return "mutation MyMutation {\n" +
                "  insert_bot_users(objects: {id: \\\"" + userId + "\\\", superset_account_username: \\\"" + supersetUsername + "\\\"}) {\n" +
                "    returning {\n" +
                "      id\n" +
                "      superset_account {\n" +
                "        password\n" +
                "        username\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
    }

    public HttpResponse<?> getFreeSupersetAccount() throws SearchException {
        return executeQuery(getRequestBody(getFreeSupersetAccountRequest()));
    }

    private String getFreeSupersetAccountRequest() {
        return "query MyQuery {\n" +
                "  bot_get_free_superset_account(limit: 1) {\n" +
                "    username\n" +
                "    password\n" +
                "  }\n" +
                "}\n";
    }

    public HttpResponse<?> getUserById(Long userId) throws SearchException {
        return executeQuery(getRequestBody(getUserByIdRequest(String.valueOf(userId))));
    }

    private String getUserByIdRequest(String userId) {
        return "query MyQuery {\n" +
                "  bot_users(where: {id: {_eq: \\\"" + userId + "\\\"}}) {\n" +
                "    id\n" +
                "    superset_account {\n" +
                "      password\n" +
                "      username\n" +
                "    }\n" +
                "  }\n" +
                "}\n";
    }

    public HttpResponse<?> getBlockByHash(String hash) throws SearchException {
        return executeQuery(getRequestBody(getBlockByHashRequest(hash)));
    }

    private String getBlockByHashRequest(String hash) {
        return "query MyQuery {\n" +
                "  likelib_blocks(where: {_or: [{hash: {_eq: \\\"" + hash + "\\\"}}," +
                "{hash: {_eq: \\\"" + hash.substring(1) + "\\\"}}," +
                "{hash: {_ilike: \\\"" + hash.substring(1) + "%\\\"}}," +
                "{hash: {_ilike: \\\"" + hash + "%\\\"}}]}, limit: 1) {\n" +
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

    public HttpResponse<?> getLastBlock() throws SearchException {
        return executeQuery(getRequestBody(getLastBlockRequest()));
    }

    private String getLastBlockRequest() {
        return "query MyQuery {\n" +
                "  likelib_blocks(order_by: {height: desc}, limit: 1) {\n" +
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

    public HttpResponse<?> getContracts() throws SearchException {
        return executeQuery(getRequestBody(getContractsRequest()));
    }

    private String getContractsRequest() {
        return "query MyQuery {\n" +
                "  likelib_accounts(order_by: {balance: desc}, limit: " + ApplicationConstants.CONTRACTS_PER_PAGE + ", where: {type: {_eq: \\\"Contract\\\"}}) {\n" +
                "    address\n" +
                "    balance\n" +
                "    transactionsByAccountTo_aggregate {\n" +
                "      aggregate {\n" +
                "        count\n" +
                "      }\n" +
                "    }\n" +
                "    transactions_aggregate {\n" +
                "      aggregate {\n" +
                "        count\n" +
                "      }\n" +
                "    }\n" +
                "  }" +
                "  likelib_accounts_aggregate {\n" +
                "    aggregate {\n" +
                "      count\n" +
                "    }\n" +
                "  }" +
                "}\n";
    }

    public HttpResponse<?> getAccounts() throws SearchException {
        return executeQuery(getRequestBody(getAccountsRequest()));
    }

    private String getAccountsRequest() {
        return "query MyQuery {\n" +
                "  likelib_accounts(order_by: {balance: desc}, limit: " + ApplicationConstants.ACCOUNTS_PER_PAGE + ") {\n" +
                "    address\n" +
                "    balance\n" +
                "    type\n" +
                "    blocks_aggregate {\n" +
                "      aggregate {\n" +
                "        count\n" +
                "      }\n" +
                "    }\n" +
                "    transactionsByAccountTo_aggregate {\n" +
                "      aggregate {\n" +
                "        count\n" +
                "      }\n" +
                "    }\n" +
                "    transactions_aggregate {\n" +
                "      aggregate {\n" +
                "        count\n" +
                "      }\n" +
                "    }\n" +
                "  }" +
                "  likelib_accounts_aggregate {\n" +
                "    aggregate {\n" +
                "      count\n" +
                "    }\n" +
                "  }" +
                "}\n";
    }

    public HttpResponse<?> getBlocks() throws SearchException {
        return executeQuery(getRequestBody(getBlocksRequest()));
    }

    private String getBlocksRequest() {
        return "query MyQuery {\n" +
                "  likelib_blocks(order_by: {height: desc}, limit: " + ApplicationConstants.BLOCKS_PER_PAGE + ") {\n" +
                "    height\n" +
                "    timestamp\n" +
                "    transactions_aggregate {\n" +
                "      aggregate {\n" +
                "        count\n" +
                "      }\n" +
                "    }" +
                "  }\n" +
                "  likelib_blocks_aggregate {\n" +
                "    aggregate {\n" +
                "      count\n" +
                "    }\n" +
                "  }" +
                "}\n";
    }

    public HttpResponse<?> getBlockByHeight(String height) throws SearchException {
        if (height !=null && height.length() > 0 && height.charAt(0) == '/')
            height = height.substring(1);
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

    public HttpResponse<?> getTransactions() throws SearchException {
        return executeQuery(getRequestBody(getTransactionsRequest()));
    }

    private String getTransactionsRequest() {
        return "query MyQuery {\n" +
                "  likelib_transactions(order_by: {timestamp: desc}, limit: " + ApplicationConstants.TRANSACTIONS_PER_PAGE + ") {\n" +
                "    hash\n" +
                "    status\n" +
                "    timestamp\n" +
                "    type\n" +
                "  }\n" +
                "  likelib_transactions_aggregate {\n" +
                "    aggregate {\n" +
                "      count\n" +
                "    }\n" +
                "  }" +
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
                "{hash: {_ilike: \\\"" + hash + "%\\\"}}]}, limit: 1) {\n" +
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
                "\\\"}}, {address: {_eq: \\\"" + address.substring(1) + "\\\"}}]}, limit: 1) {\n" +
                "    address\n" +
                "    balance\n" +
                "    nonce\n" +
                "    type\n" +
                "    transactions(order_by: {timestamp: desc}, limit: " + ApplicationConstants.ACCOUNT_TXS_OUT_PER_PAGE + ") {\n" +
                "      hash\n" +
                "    }\n" +
                "    transactionsByAccountTo(order_by: {timestamp: desc}, limit: " + ApplicationConstants.ACCOUNT_TXS_IN_PER_PAGE + ") {\n" +
                "      hash\n" +
                "    }" +
                "    blocks(order_by: {timestamp: desc}, limit: " + ApplicationConstants.ACCOUNT_BLOCKS_PER_PAGE + ") {\n" +
                "      height\n" +
                "    }\n" +
                "    blocks_aggregate {\n" +
                "      aggregate {\n" +
                "        count\n" +
                "      }\n" +
                "    }\n" +
                "    transactionsByAccountTo_aggregate {\n" +
                "      aggregate {\n" +
                "        count\n" +
                "      }\n" +
                "    }\n" +
                "    transactions_aggregate {\n" +
                "      aggregate {\n" +
                "        count\n" +
                "      }\n" +
                "    }" +
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
        private static final SearchService INSTANCE = new SearchService();
    }
}
