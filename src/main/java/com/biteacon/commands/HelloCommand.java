package com.biteacon.commands;

import com.biteacon.constants.Messages;
import com.biteacon.entities.CommandResponse;

public class HelloCommand implements Command {
    @Override
    public CommandResponse execute(String key) {
        return new CommandResponse(Messages.HELLO_COMMAND);
    }
}
