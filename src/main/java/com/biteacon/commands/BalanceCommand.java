package com.biteacon.commands;

public class BalanceCommand implements Command {
    @Override
    public String execute(String key) {
        return "Hello from balance command!";
    }
}
