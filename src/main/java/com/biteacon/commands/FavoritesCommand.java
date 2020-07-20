package com.biteacon.commands;

public class FavoritesCommand implements Command {
    @Override
    public String execute(String key) {
        return "Hello from favorites command!";
    }
}
