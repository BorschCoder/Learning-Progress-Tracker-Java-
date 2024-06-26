package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.Server;
import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.Input;
import com.company.tracker.service.impl.StudentServiceImpl;

import java.util.ResourceBundle;

import static com.company.tracker.controller.ResponseType.CREDENTIALS_STUDENTS;
import static com.company.tracker.controller.command.CommandType.ADD_STUDENTS;

public class AddStudentCommand implements Command, Input {

    private final ResourceBundle bundle;
    private final StudentServiceImpl service;

    public AddStudentCommand(ResourceBundle bundle) {
        this.bundle = bundle;
        this.service = StudentServiceImpl.getInstance();
    }

    @Override
    public String execute(String request) {
        if (request.equalsIgnoreCase(ADD_STUDENTS.get())) {
            Server.enableInputMode();
            return bundle.getString(CREDENTIALS_STUDENTS.name());
        }
        return bundle.getString(service.add(request).getType().name());
    }


}
