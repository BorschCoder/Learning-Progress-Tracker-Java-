package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.command.Command;

public class Undefined implements Command {
    @Override
    public String execute(String request) {
        return null;
    }
}
