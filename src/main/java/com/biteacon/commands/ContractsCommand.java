package com.biteacon.commands;

import com.biteacon.POJOs.GraphqlResponse;
import com.biteacon.entities.CommandRequest;
import com.biteacon.entities.CommandResponse;
import com.biteacon.exceptions.SearchException;
import com.biteacon.services.SearchService;
import com.biteacon.services.TransformationService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.http.HttpResponse;

public class ContractsCommand implements Command {
    Gson gson = new Gson();

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            HttpResponse<?> response = SearchService.getInstance().getContracts();
            String responseBodyString = response.body().toString();
            GraphqlResponse contracts = gson.fromJson(responseBodyString, GraphqlResponse.class);
            String formattedAccounts = TransformationService.getInstance().getFormattedContracts(contracts);
            if (formattedAccounts != null)
                return new CommandResponse(formattedAccounts);
        } catch (SearchException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
