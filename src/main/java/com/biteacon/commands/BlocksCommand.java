package com.biteacon.commands;

public class BlocksCommand implements Command{
    @Override
    public String execute(String key) {
        return "Hello from blocks command!";
    }
}
