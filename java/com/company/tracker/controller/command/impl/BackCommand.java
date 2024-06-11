package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.controller.Server;
import com.company.tracker.controller.command.Command;
import com.company.tracker.service.impl.StudentServiceImpl;

import java.util.ResourceBundle;

public class BackCommand implements Command {
    private final ResourceBundle bundle;
    public BackCommand(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    @Override
    public String execute(String request) {
        String backString = "Enter 'exit' to exit the program.";
        if (Server.inputMode()) {
             backString= String.format(bundle.getString(ResponseType.BACK.name())
                    , StudentServiceImpl.getCountStudent());
             Server.disableInputMode();
        }
        return backString;
    }
}
