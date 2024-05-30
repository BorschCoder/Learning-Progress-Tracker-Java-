package com.company.tracker.controller.command;

import com.company.tracker.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static com.company.tracker.controller.command.CommandType.*;

public final class CommandProvider {

    public static final String RESPONSE_BUNDLE = "response";
    private final ResourceBundle bundle;
    private static final Map<CommandType, Command> repository = new HashMap<>();

    public CommandProvider() {
        this.bundle = ResourceBundle.getBundle(RESPONSE_BUNDLE);
        repository.put(ADD, new AddStudent(bundle));
        repository.put(HELP, new Help(bundle));
        repository.put(BACK, new Back(bundle));
        repository.put(UNDEFINED, new Undefined(bundle));
        repository.put(EXIT, new Exit(bundle, repository.get(UNDEFINED)));

    }

    public Command getCommand(String nameCommand) {

        CommandType commandType = null;
        Command commandInstance = null;
        String ejectCommand = ejectCommand(nameCommand);
        try {
            commandType = CommandType.valueOf(ejectCommand);
            commandInstance = repository.get(commandType);
        } catch (IllegalArgumentException | NullPointerException e) {
            commandInstance = repository.get(UNDEFINED);
        }
        return commandInstance;
    }

    private String ejectCommand(String nameCommand) {
        return nameCommand.split(" ")[0].toUpperCase();
    }
}
