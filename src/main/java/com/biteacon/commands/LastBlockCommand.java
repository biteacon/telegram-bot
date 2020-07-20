package com.biteacon.commands;

public class LastBlockCommand implements Command {
    @Override
    public String execute(String key) {
        return "Hello from last block command!";
    }
}
