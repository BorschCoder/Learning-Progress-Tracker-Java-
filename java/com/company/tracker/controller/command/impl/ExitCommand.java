package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.Server;
import com.company.tracker.controller.command.Command;

import java.util.ResourceBundle;

import static com.company.tracker.controller.ResponseType.EXIT;

public class ExitCommand implements Command {
    private final ResourceBundle bundle;
    private final Command errorCommand;

    public ExitCommand(ResourceBundle bundle, Command errorCommand) {
        this.bundle = bundle;
        this.errorCommand = errorCommand;
    }

    @Override
    public String execute(String request) {
        if (!request.equalsIgnoreCase(EXIT.name())) {
            return errorCommand.execute(request);
        }

        Server.close();
        return bundle.getString(EXIT.name());
    }
}
