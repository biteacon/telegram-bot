package com.biteacon.commands.brute_commands;

import com.biteacon.POJOs.BlockResponse;
import com.biteacon.commands.Command;
import com.biteacon.exceptions.SearchException;
import com.biteacon.services.SearchService;
import com.biteacon.services.TransformationService;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BlockByHeightCommand implements Command {
    Gson gson;

    public BlockByHeightCommand() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
            @Override
            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_DATE_TIME);
            }
        }).create();
    }

    @Override
    public String execute(String key) {
        //        todo: validate + transformation
        try {
            HttpResponse<?> response = SearchService.getInstance().getBlockByHeight(key);
            String responseBodyString = response.body().toString();
            BlockResponse block = gson.fromJson(responseBodyString, BlockResponse.class);
            return TransformationService.getInstance().getFormattedBlock(block);
        } catch (SearchException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
