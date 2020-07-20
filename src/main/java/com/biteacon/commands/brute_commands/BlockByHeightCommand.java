package com.biteacon.commands.brute_commands;

import com.biteacon.commands.Command;
import com.biteacon.constants.Messages;
import com.biteacon.services.SearchService;

import java.io.IOException;

public class BlockByHeightCommand implements Command {
    @Override
    public String execute(String key) {
        //        todo: validate + transformation
        try {
            return SearchService.getInstance().getBlockByHeight(key);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Messages.NOT_FOUND;
    }
}
