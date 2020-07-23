package com.biteacon.commands;

import com.biteacon.entities.CommandResponse;

public class FavoritesCommand implements Command {
    @Override
    public CommandResponse execute(String key) {
        return new CommandResponse("Hello from favorites command!");
    }
}
