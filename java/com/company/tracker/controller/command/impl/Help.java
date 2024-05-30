package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.CommandType;

import java.util.ResourceBundle;

public class Help implements Command {
    private final ResourceBundle bundle;
    public Help(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    @Override
    public String execute(String request) {
        return ResponseType.HELP.name();
    }
}
