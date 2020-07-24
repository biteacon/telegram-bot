package com.biteacon.commands;

import com.biteacon.POJOs.GraphqlResponse;
import com.biteacon.entities.CommandResponse;
import com.biteacon.exceptions.SearchException;
import com.biteacon.services.SearchService;
import com.biteacon.services.TransformationService;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionsCommand implements Command {
    Gson gson;

    public TransactionsCommand() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_DATE_TIME);
            }
        }).create();
    }

    @Override
    public CommandResponse execute(String key) {
        try {
            HttpResponse<?> response = SearchService.getInstance().getTransactions();
            String responseBodyString = response.body().toString();
            GraphqlResponse transactions = gson.fromJson(responseBodyString, GraphqlResponse.class);
            String formattedTransaction = TransformationService.getInstance().getFormattedTransactions(transactions);
            if (formattedTransaction != null)
                return new CommandResponse(formattedTransaction);
        } catch (SearchException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
