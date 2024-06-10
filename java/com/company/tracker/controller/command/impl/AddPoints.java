package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.Server;
import com.company.tracker.controller.command.Command;
import com.company.tracker.service.impl.StudentServiceImpl;

import java.util.ResourceBundle;

import static com.company.tracker.controller.ResponseType.Credentials_STUDENTS;
import static com.company.tracker.controller.command.CommandType.ADD_POITNS;

public class AddPoints implements Command {
    private final ResourceBundle bundle;
    private final StudentServiceImpl service;

    public AddPoints(ResourceBundle bundle) {
        this.bundle = bundle;
        this.service = StudentServiceImpl.getInstance();
    }

    @Override
    public String execute(String request) {
        if (request.equalsIgnoreCase(ADD_POITNS.get())) {
            Server.enableInputMode();
            return bundle.getString(ADD_POITNS.name());
        }
        return bundle.getString(service.add_points(request).name());
    }
}
