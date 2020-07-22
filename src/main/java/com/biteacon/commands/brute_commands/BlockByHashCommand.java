package com.biteacon.commands.brute_commands;

import com.biteacon.POJOs.AccountByAddrReq;
import com.biteacon.POJOs.BlockByHashReq;
import com.biteacon.commands.Command;
import com.biteacon.constants.Messages;
import com.biteacon.services.SearchService;
import com.google.gson.Gson;

import java.io.IOException;

public class BlockByHashCommand implements Command {
    Gson gson = new Gson();

    @Override
    public String execute(String key) {
        //        todo: validate + transformation
        try {
            String block = SearchService.getInstance().getBlockByHash(key);
            BlockByHashReq bl = gson.fromJson(block, BlockByHashReq.class);
            return block;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Messages.NOT_FOUND;
    }
}
