package com.biteacon.commands;

import com.biteacon.entities.CommandRequest;
import com.biteacon.entities.CommandResponse;

public class FavoritesCommand implements Command {
    @Override
    public CommandResponse execute(CommandRequest request) {
        return new CommandResponse("Hello from favorites command!");
    }
}
