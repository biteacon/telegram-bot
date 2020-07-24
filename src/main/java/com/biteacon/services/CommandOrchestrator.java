package com.biteacon.services;

import com.biteacon.commands.*;
import com.biteacon.commands.brute_commands.AccountByAddressCommand;
import com.biteacon.commands.brute_commands.BlockByHashCommand;
import com.biteacon.commands.brute_commands.BlockByHeightCommand;
import com.biteacon.commands.brute_commands.TransactionByHashCommand;
import com.biteacon.constants.Commands;
import com.biteacon.constants.Messages;
import com.biteacon.entities.CommandResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandOrchestrator {
    private final Map<String, Command> commands;
    private final Map<String, Command> bruteCommands;

    public static CommandOrchestrator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private CommandOrchestrator() {
        HelpCommand helpCommand = new HelpCommand();
        StartCommand startCommand = new StartCommand();
        LastBlockCommand lastBlockCommand = new LastBlockCommand();
        HelloCommand helloCommand = new HelloCommand();
        BlocksCommand blocksCommand = new BlocksCommand();
        AccountsCommand accountsCommand = new AccountsCommand();
        TransactionsCommand transactionsCommand = new TransactionsCommand();
        ContractsCommand contractsCommand = new ContractsCommand();
        commands = new HashMap<>(){{
            put(Commands.HELP, helpCommand);
            put(Commands.HELP2, helpCommand);
            put(Commands.START, startCommand);
            put(Commands.START2, startCommand);
            put(Commands.LAST_BLOCK, lastBlockCommand);
            put(Commands.LAST_BLOCK2, lastBlockCommand);
            put(Commands.HELLO, helloCommand);
            put(Commands.HELLO2, helloCommand);
            put(Commands.HELLO3, helloCommand);
            put(Commands.HELLO4, helloCommand);
            put(Commands.BLOCKS, blocksCommand);
            put(Commands.BLOCKS2, blocksCommand);
            put(Commands.ACCOUNTS, accountsCommand);
            put(Commands.ACCOUNTS2, accountsCommand);
            put(Commands.TRANSACTIONS, transactionsCommand);
            put(Commands.TRANSACTIONS2, transactionsCommand);
            put(Commands.CONTRACTS, contractsCommand);
            put(Commands.CONTRACTS2, contractsCommand);
        }};
        bruteCommands = new HashMap<>(){{
            put(Commands.ACCOUNT_BY_ADDRESS, new AccountByAddressCommand());
            put(Commands.BLOCK_BY_HASH, new BlockByHashCommand());
            put(Commands.BLOCK_BY_HEIGHT, new BlockByHeightCommand());
            put(Commands.TRANSACTION_BY_HASH, new TransactionByHashCommand());
        }};
    }

    public CommandResponse matchCommands(String request) {
        Command command = commands.get(request.toLowerCase());
        if (command == null) {
            for (String commandName : getCommandNamesByRequest(request)) {
                CommandResponse result = bruteCommands.get(commandName).execute(request);
                if (result != null) //todo:change it (error code or something else)
                    return result;
            }
//            todo: error
            return new CommandResponse(Messages.NOT_FOUND);
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
