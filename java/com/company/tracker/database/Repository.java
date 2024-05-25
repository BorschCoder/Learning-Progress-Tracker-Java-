package com.company.tracker.database;

import com.company.tracker.database.Database;

public class Repository {
    private final Database database;

    public Repository() {
        this.database = new Database();
    }
}
