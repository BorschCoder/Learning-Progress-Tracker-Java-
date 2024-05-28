package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.CommandType;
import com.company.tracker.controller.command.Input;
import com.company.tracker.service.impl.StudentService;

import java.util.ResourceBundle;

import static com.company.tracker.controller.ResponseType.ADDED;
import static com.company.tracker.controller.ResponseType.ADD_STUDENTS;

public class AddStudent implements Command, Input {

    private final ResourceBundle bundle;
    private final StudentService service;

    public AddStudent(ResourceBundle bundle) {
        this.bundle = bundle;
        this.service = new StudentService();
    }

    @Override
    public String execute(String request) {
        if (request.equalsIgnoreCase(CommandType.ADD.name())) {
            return bundle.getString(ADD_STUDENTS.name());
        }
        // TODO: 28.05.2024 student validators - validate strings before createing/saving students move to the service (validator)
        //todo sent to service for creating student and wait answer or throw catch exceptions

        return bundle.getString(ADDED.name());
    }


}
