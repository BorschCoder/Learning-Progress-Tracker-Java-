package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.controller.Server;
import com.company.tracker.controller.command.Command;
import com.company.tracker.entity.Response;
import com.company.tracker.service.EmailService;
import com.company.tracker.service.StatisticsService;
import com.company.tracker.service.impl.EmailServiceImp;
import com.company.tracker.service.impl.StatisticsServiceImpl;

import java.util.ResourceBundle;

import static com.company.tracker.controller.command.CommandType.NOTIFY;
import static com.company.tracker.controller.command.CommandType.STATISTICS;

public class NotifyCommand implements Command {

    private final EmailService service;
    private final ResourceBundle bundle;

    public NotifyCommand(ResourceBundle bundle) {
        this.service = EmailServiceImp.getInstance();
        this.bundle = bundle;
    }

    @Override
    public String execute(String request) {

        if (request.equalsIgnoreCase(NOTIFY.get())) {
            return service.notifyCommand().getStatisticsString();
        }
        return bundle.getString(ResponseType.UNDEFINED.name());
    }
}