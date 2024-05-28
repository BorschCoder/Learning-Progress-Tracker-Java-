package com.company.tracker.controller.command;

import com.company.tracker.controller.command.impl.AddStudent;
import com.company.tracker.controller.command.impl.Exit;
import com.company.tracker.controller.command.impl.Help;
import com.company.tracker.controller.command.impl.Undefined;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public final class CommandProvider {

    public static final String RESPONSE_BUNDLE = "response";
    private final ResourceBundle bundle;
    private static final Map<CommandType, Command> repository = new HashMap<>();

    public CommandProvider() {
        this.bundle = ResourceBundle.getBundle(RESPONSE_BUNDLE);
        repository.put(CommandType.ADD, new AddStudent(bundle));
        repository.put(CommandType.EXIT, new Exit());
        repository.put(CommandType.HELP, new Help());
        repository.put(CommandType.BACK, new Help());
        repository.put(CommandType.UNDEFINED, new Undefined());

    }

    public Command getCommand(String nameCommand) {
        CommandType commandType = null;
        Command commandInstance = null;
        try {
            commandType = CommandType.valueOf(nameCommand.toUpperCase());
            commandInstance = repository.get(commandType);
        } catch (IllegalArgumentException | NullPointerException e) {
            commandInstance = repository.get(CommandType.UNDEFINED);
        }
        return commandInstance;
    }
}
