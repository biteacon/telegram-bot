package com.biteacon.commands.brute_commands;

import com.biteacon.POJOs.AccountByAddrReq;
import com.biteacon.commands.Command;
import com.biteacon.constants.Messages;
import com.biteacon.services.SearchService;
import com.google.gson.Gson;

import java.io.IOException;

public class AccountByAddressCommand implements Command {
    Gson gson = new Gson();

    @Override
    public String execute(String key) {
        //        todo: validate + transformation
        try {
            String account = SearchService.getInstance().getAccountByAddress(key);
            AccountByAddrReq acc = gson.fromJson(account, AccountByAddrReq.class);
            return account;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Messages.NOT_FOUND;
    }
}
