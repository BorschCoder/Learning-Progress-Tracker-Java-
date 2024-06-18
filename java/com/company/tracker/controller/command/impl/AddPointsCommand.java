package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.Server;
import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.Input;
import com.company.tracker.entity.Response;
import com.company.tracker.service.StudentService;
import com.company.tracker.service.impl.StudentServiceImpl;

import java.util.Collections;
import java.util.ResourceBundle;

import static com.company.tracker.controller.command.CommandType.ADD_POINTS;

public class AddPointsCommand implements Command, Input {
    private final ResourceBundle bundle;
    private final StudentService service;

    public AddPointsCommand(ResourceBundle bundle) {
        this.bundle = bundle;
        this.service = StudentServiceImpl.getInstance();
    }

    @Override
    public String execute(String request) {
        if (request.equalsIgnoreCase(ADD_POINTS.get())) {
            Server.enableInputMode();
            return bundle.getString(ADD_POINTS.name());
        }
        Response response = service.addPoints(request);

        return String.format(bundle.getString(response.getType().name()), response.getStudentStringId());
    }
}
