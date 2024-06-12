package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.Server;
import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.Input;
import com.company.tracker.entity.Response;
import com.company.tracker.service.StudentService;
import com.company.tracker.service.impl.StudentServiceImpl;

import java.util.Map;
import java.util.ResourceBundle;

import static com.company.tracker.controller.ResponseType.NO_STUDENTS_BY_ID;
import static com.company.tracker.controller.ResponseType.SHOW_STATS;
import static com.company.tracker.controller.command.CommandType.FIND;
import static com.company.tracker.entity.Course.*;

public class FindCommand implements Command, Input {
    private final ResourceBundle bundle;
    private final StudentService service;

    public FindCommand(ResourceBundle bundle) {
        this.bundle = bundle;
        this.service = StudentServiceImpl.getInstance();
    }

    @Override
    public String execute(String request) {

        if (request.equalsIgnoreCase(FIND.get())) {
            Server.enableInputMode();
            return bundle.getString(FIND.name());
        }
        int studentID = Integer.parseInt(request);
        Response response = service.getStudentById(studentID);

        if (response.getStudentId() == 0) {
            return String.format(bundle.getString(NO_STUDENTS_BY_ID.name()), studentID);
        }

        Map statistics = response.getStatDTO();
        String responseString = String.format(bundle.getString(SHOW_STATS.name()),
                studentID
                , statistics.get(JAVA)
                , statistics.get(DSA)
                , statistics.get(DATABASES)
                , statistics.get(SPRING));

        return responseString;
    }
}
