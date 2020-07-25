package com.biteacon.commands;

import com.biteacon.constants.Messages;
import com.biteacon.entities.CommandRequest;
import com.biteacon.entities.CommandResponse;

public class InviteMeCommand implements Command {
    @Override
    public CommandResponse execute(CommandRequest request) {
        return new CommandResponse(Messages.INVITE_ME_COMMAND);
    }
}