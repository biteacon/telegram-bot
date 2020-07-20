package com.biteacon;

import com.biteacon.commands.Command;
import com.biteacon.commands.HelpCommand;
import com.biteacon.commands.StartCommand;
import com.biteacon.constants.Commands;
import com.biteacon.services.ResponseService;

import java.util.HashMap;
import java.util.Map;

public class CommandFilter {
    private Map<String, Command> commands;

    public static CommandFilter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private CommandFilter() {
        commands = new HashMap<>(){{
            put(Commands.HELP, new HelpCommand());
            put(Commands.START, new StartCommand());
        }};
    }

    public String matchCommands(String userCommand) {
        Command command = commands.get(userCommand);
        if (command != null)
            return command.execute(userCommand);
        if (isInteger(userCommand))
            return ResponseService.getInstance().getBlockByHeight(userCommand);
        return ResponseService.getInstance().getAccountByAddress(userCommand) + "\n\n" +
                ResponseService.getInstance().getTransactionByHash(userCommand) + "\n\n" +
                ResponseService.getInstance().getBlockByHash(userCommand);
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    private static class SingletonHolder {
        private static CommandFilter INSTANCE = new CommandFilter();
    }
}
