package com.biteacon;

import com.biteacon.commands.Command;
import com.biteacon.commands.HelpCommand;
import com.biteacon.commands.StartCommand;
import com.biteacon.commands.brute_commands.AccountByAddressCommand;
import com.biteacon.commands.brute_commands.BlockByHashCommand;
import com.biteacon.commands.brute_commands.BlockByHeightCommand;
import com.biteacon.commands.brute_commands.TransactionByHashCommand;
import com.biteacon.constants.Commands;
import com.biteacon.constants.Messages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandOrchestrator {
    private Map<String, Command> commands;
    private Map<String, Command> bruteCommands;

    public static CommandOrchestrator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private CommandOrchestrator() {
        commands = new HashMap<>(){{
            put(Commands.HELP, new HelpCommand());
            put(Commands.HELP2, new HelpCommand());
            put(Commands.START, new StartCommand());
            put(Commands.START2, new StartCommand());
        }};
        bruteCommands = new HashMap<>(){{
            put(Commands.ACCOUNT_BY_ADDRESS, new AccountByAddressCommand());
            put(Commands.BLOCK_BY_HASH, new BlockByHashCommand());
            put(Commands.BLOCK_BY_HEIGHT, new BlockByHeightCommand());
            put(Commands.TRANSACTION_BY_HASH, new TransactionByHashCommand());
        }};
    }

    public String matchCommands(String request) {
        Command command = commands.get(request.toLowerCase());
        if (command == null) {
            for (String commandName : getCommandNamesByRequest(request)) {
                String result = bruteCommands.get(commandName).execute(request);
                if (result != null) //todo:change it (error code or something else)
                    return result;
            }
//            todo: error
            return Messages.NOT_FOUND;
        }
        return command.execute(request);
    }

    private List<String> getCommandNamesByRequest(String request) {
        ArrayList<String> commandNames = new ArrayList<>();
        if (isInteger(request) || isInteger(request.substring(1)))
            commandNames.add(Commands.BLOCK_BY_HEIGHT);
        else {
            commandNames.add(Commands.ACCOUNT_BY_ADDRESS);
            commandNames.add(Commands.TRANSACTION_BY_HASH);
            commandNames.add(Commands.BLOCK_BY_HASH);
        }
        return commandNames;
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    private static class SingletonHolder {
        private static final CommandOrchestrator INSTANCE = new CommandOrchestrator();
    }
}
