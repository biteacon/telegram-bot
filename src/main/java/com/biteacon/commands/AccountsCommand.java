package com.biteacon.commands;

public class AccountsCommand implements Command {
    @Override
    public String execute(String key) {
        return "Hello from accounts command!";
    }
}
