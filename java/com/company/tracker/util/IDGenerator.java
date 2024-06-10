package com.company.tracker.util;

public class IDGenerator {
    private static int currentID;

    public static int getCurrentID() {
        if (currentID == 0) {
            currentID = 10000;
        }
        int id = currentID;
        incremetCurrentID();

        return id;
    }

    public static void incremetCurrentID() {
        currentID += 1;
    }
}
