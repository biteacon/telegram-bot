package com.biteacon;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        first();
        second();
        third();
        System.out.println("----------------------------------");
        System.out.println("{ \"query\": \"<paste here>\" }");
    }

    private static void first() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String body = "{ \"query\": \"query MyQuery {\nlikelib_account_types {\ntype\n}\n}\n\" }";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/v1/graphql"))
//                .timeout(Duration.ofMinutes(1))
                .headers("Content-type", "application/json",
                        "Accept", "text/plain",
                        "x-hasura-admin-secret", "changeme2")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<?> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    private static void second() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String body = "{ \"query\": \"query MyQuery {\n" +
                "  likelib_accounts(where: {type: {_eq: \\\"Client\\\"}}) {\n" +
                "    address\n" +
                "    balance\n" +
                "    type\n" +
                "  }\n" +
                "}\n\" }";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/v1/graphql"))
//                .timeout(Duration.ofMinutes(1))
                .headers("Content-type", "application/json",
                        "Accept", "text/plain",
                        "x-hasura-admin-secret", "changeme2")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<?> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    private static void third() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String body = "{ \"query\": \"query MyQuery {\n" +
                "  likelib_accounts(where: {transactions: {block: {height: {_eq: \\\"1\\\"}}}}) {\n" +
                "    address\n" +
                "    balance\n" +
                "    type\n" +
                "    transactions {\n" +
                "      hash\n" +
                "    }\n" +
                "  }\n" +
                "}\n\" }";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/v1/graphql"))
//                .timeout(Duration.ofMinutes(1))
                .headers("Content-type", "application/json",
                        "Accept", "text/plain",
                        "x-hasura-admin-secret", "changeme2")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<?> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
