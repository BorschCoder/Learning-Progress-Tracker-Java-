package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.CommandType;

public class Help implements Command {
    @Override
    public String execute(String request) {
        return ResponseType.HELP.name();
    }
}
