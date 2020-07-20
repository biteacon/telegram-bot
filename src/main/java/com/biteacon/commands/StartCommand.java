package com.biteacon.commands;

public class StartCommand implements Command{
    @Override
    public String execute(String key) {
        return "Hello from start command!";
    }
}
