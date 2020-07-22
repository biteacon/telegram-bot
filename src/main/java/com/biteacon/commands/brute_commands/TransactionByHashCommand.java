package com.biteacon.commands.brute_commands;

import com.biteacon.POJOs.TransactionByHashReq;
import com.biteacon.commands.Command;
import com.biteacon.constants.Messages;
import com.biteacon.services.SearchService;
import com.google.gson.Gson;

import java.io.IOException;

public class TransactionByHashCommand implements Command {
    Gson gson = new Gson();

    @Override
    public String execute(String key) {
        //        todo: validate + transformation
        try {
            String transaction = SearchService.getInstance().getTransactionByHash(key);
            TransactionByHashReq tx = gson.fromJson(transaction, TransactionByHashReq.class);
            return transaction;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Messages.NOT_FOUND;
    }
}
