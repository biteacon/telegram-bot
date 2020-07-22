package com.biteacon.commands.brute_commands;

import com.biteacon.POJOs.TransactionResponse;
import com.biteacon.commands.Command;
import com.biteacon.exceptions.SearchException;
import com.biteacon.services.SearchService;
import com.biteacon.services.TransformationService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.http.HttpResponse;

public class TransactionByHashCommand implements Command {
    Gson gson = new Gson();

    @Override
    public String execute(String key) {
        //        todo: validate + transformation
        try {
            HttpResponse<?> response = SearchService.getInstance().getTransactionByHash(key);
            String responseBodyString = response.body().toString();
            TransactionResponse transaction = gson.fromJson(responseBodyString, TransactionResponse.class);
            return TransformationService.getInstance().getFormattedTransaction(transaction);
        } catch (SearchException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
