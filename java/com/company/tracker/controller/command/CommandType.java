package com.company.tracker.controller.command;

public enum CommandType {
    EXIT("exit"),
    HELP("help"),
    LIST("list"),
    BACK("back"),
    ADD_STUDENTS("add students"),
    FIND("find"),
    ADD_POINTS("add points"),
    STATISTICS("statistics"),
    NOTIFY("notify"),
    UNDEFINED("");
    private final String type;

    CommandType(String type) {
        this.type = type;
    }

    public String get() {
        return type;
    }
}
