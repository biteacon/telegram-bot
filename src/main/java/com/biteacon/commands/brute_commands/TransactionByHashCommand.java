package com.biteacon.commands.brute_commands;

import com.biteacon.commands.Command;

public class TransactionByHashCommand implements Command {
    @Override
    public String execute(String key) {
        return "Hello from transaction by hash command!";
    }
}
