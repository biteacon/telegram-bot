package com.biteacon.commands;

public class TransactionsCommand implements Command {
    @Override
    public String execute(String key) {
        return "Hello from transactions command!";
    }
}
