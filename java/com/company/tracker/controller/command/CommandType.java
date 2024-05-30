package com.company.tracker.controller.command;

public enum CommandType {
    EXIT("exit"),
    HELP("help"),
    BACK("back"),
    ADD("add students"),
    UNDEFINED("");
    private final String type;

    CommandType(String type) {
        this.type = type;
    }

    public String get() {
        return type;
    }
}
