package com.company.tracker.entity;

import java.util.List;

public enum Course {

    JAVA("JAVA"),
    SPRING("SPRING"),
    DSA("DSA"),
    DATABASES("DATABASES"),
    UNDEFINED("UNDEFINED");
    private final String type;

    Course(String type) {
        this.type = type;
    }
    public String get() {
        return type;
    }
}
