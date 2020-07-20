package com.biteacon.commands.brute_commands;

import com.biteacon.commands.Command;

public class AccountByAddressCommand implements Command {
    @Override
    public String execute(String key) {
        return "Hello from account by address command!";
    }
}
