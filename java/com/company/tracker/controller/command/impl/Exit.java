package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.controller.Server;
import com.company.tracker.controller.command.Command;
import com.company.tracker.controller.command.CommandType;

public class Exit implements Command {
    @Override
    public String execute(String request) {

        System.out.println("Bye!");
        Runnable close = Server::close;

        return ResponseType.EXIT.name();
    }
}
