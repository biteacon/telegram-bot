package com.biteacon.commands;

import com.biteacon.constants.Messages;
import com.biteacon.entities.CommandResponse;

public class HelpCommand implements Command {
    @Override
    public CommandResponse execute(String key) {
        return new CommandResponse(Messages.HELP_COMMAND);
    }
}
