package com.company.tracker.controller.command;

import com.company.tracker.controller.ResponseType;

public interface Command {
    String execute(String request);
}
