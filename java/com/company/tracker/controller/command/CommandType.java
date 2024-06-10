package com.company.tracker.controller.command;

public enum CommandType {
    EXIT("exit"),
    HELP("help"),
    LIST("list"),
    BACK("back"),
    ADD("add students"),
    ADD_POITNS("add points"),
    UNDEFINED("");
    private final String type;

    CommandType(String type) {
        this.type = type;
    }

    public String get() {
        return type;
    }
}
