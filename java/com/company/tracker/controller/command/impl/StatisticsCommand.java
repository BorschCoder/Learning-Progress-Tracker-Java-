package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.Server;
import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.Input;
import com.company.tracker.entity.Response;
import com.company.tracker.service.StatisticsService;
import com.company.tracker.service.impl.StatisticsServiceImpl;

import java.util.ResourceBundle;

import static com.company.tracker.controller.command.CommandType.STATISTICS;

public class StatisticsCommand implements Command, Input {
    private final ResourceBundle bundle;
    private final StatisticsService service;

    public StatisticsCommand(ResourceBundle bundle) {
        this.bundle = bundle;
        this.service = StatisticsServiceImpl.getInstance();
    }

    @Override
    public String execute(String request) {

        if (request.equalsIgnoreCase(STATISTICS.get())) {
            Server.enableInputMode();
            return bundle.getString(STATISTICS.name());
        }
        service.updateGeneralStatistic();
       Response response = service.getCategoryStatistic();
        return null;
//        return String.format(bundle.getString(response.getType().name()), response.getStudentStringId());
    }
}
