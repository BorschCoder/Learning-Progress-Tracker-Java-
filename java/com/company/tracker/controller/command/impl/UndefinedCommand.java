package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.controller.command.Command;

import java.util.ResourceBundle;

public class UndefinedCommand implements Command {
    private final ResourceBundle bundle;

    public UndefinedCommand(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public String execute(String request) {
        return bundle.getString(ResponseType.UNDEFINED.name());
    }
}
