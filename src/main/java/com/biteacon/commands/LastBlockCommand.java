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

public class LastBlockCommand implements Command {
    Gson gson;

    public LastBlockCommand() {
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
            HttpResponse<?> response = SearchService.getInstance().getBlockByHeight(key);
            String responseBodyString = response.body().toString();
            GraphqlResponse block = gson.fromJson(responseBodyString, GraphqlResponse.class);
            if (block == null || block.getData() == null) {
                response = SearchService.getInstance().getLastBlock();
                responseBodyString = response.body().toString();
                block = gson.fromJson(responseBodyString, GraphqlResponse.class);
            }
            String formattedBlock = TransformationService.getInstance().getFormattedBlock(block);
            return new CommandResponse(formattedBlock);
        } catch (SearchException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
