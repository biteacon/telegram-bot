package com.biteacon.commands.brute_commands;

import com.biteacon.POJOs.BlockByHeightReq;
import com.biteacon.commands.Command;
import com.biteacon.constants.Messages;
import com.biteacon.services.SearchService;
import com.google.gson.Gson;

import java.io.IOException;

public class BlockByHeightCommand implements Command {
    Gson gson = new Gson();

    @Override
    public String execute(String key) {
        //        todo: validate + transformation
        try {
            String block = SearchService.getInstance().getBlockByHeight(key);
            BlockByHeightReq bl = gson.fromJson(block, BlockByHeightReq.class);
            return block;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Messages.NOT_FOUND;
    }
}
