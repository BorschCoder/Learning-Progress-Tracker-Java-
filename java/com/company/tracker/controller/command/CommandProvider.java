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
        repository.put(ADD, new AddStudentCommand(bundle));
        repository.put(ADD_POINTS, new AddPointsCommand(bundle));
        repository.put(HELP, new HelpCommand(bundle));
        repository.put(BACK, new BackCommand(bundle));
        repository.put(UNDEFINED, new UndefinedCommand(bundle));
        repository.put(LIST, new ListCommand(bundle));
        repository.put(EXIT, new ExitCommand(bundle, repository.get(UNDEFINED)));

    }

    // TODO: 11.06.2024 preprocessing - if has SPACE ' ' char -> replace with UNDERSCORE '_' before calling 'valueOf' 

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
