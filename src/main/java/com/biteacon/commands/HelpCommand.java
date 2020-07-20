package com.biteacon.commands;

public class HelpCommand implements Command {
    @Override
    public String execute(String key) {
        return "Hello from help command!";
    }
}
