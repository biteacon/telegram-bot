package com.biteacon.commands.brute_commands;

import com.biteacon.commands.Command;

public class BlockByHeightCommand implements Command {
    @Override
    public String execute(String key) {
        return "Hello from block by height command!";
    }
}
