package com.biteacon.commands;

import com.biteacon.entities.CommandResponse;

public class LastBlockCommand implements Command {
    @Override
    public CommandResponse execute(String key) {
        return new CommandResponse("Hello from last block command!");
    }
}
