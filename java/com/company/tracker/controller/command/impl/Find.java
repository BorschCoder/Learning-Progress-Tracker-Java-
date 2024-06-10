package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.command.Command;
import com.company.tracker.service.impl.StudentServiceImpl;

import java.util.ResourceBundle;

public class Find implements Command {
    private final ResourceBundle bundle;
    private final StudentServiceImpl service;

    public Find(ResourceBundle bundle) {
        this.bundle = bundle;
        this.service = StudentServiceImpl.getInstance();
    }

    @Override
    public String execute(String request) {
        return null;
    }
}
