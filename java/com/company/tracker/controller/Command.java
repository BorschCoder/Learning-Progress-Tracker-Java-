package com.company.tracker.controller;

public enum Command {
    EXIT("exit"),
    HELP("help"),
    BACK("back"),
    ADD("add students");
    private final String type;

    Command(String type) {
        this.type = type;
    }

    public String getCommand() {
        return type;
    }
}
