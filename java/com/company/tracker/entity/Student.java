package com.company.tracker.entity;

import com.company.tracker.util.IDGenerator;

import java.util.List;

public class Student {

    private String firstName;
    private String secondName;
    private String lastName;
    private int id;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    private  String email;

    private Statistics statistics;

    public Student(String firstName, String secondName, String lastName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.id = IDGenerator.getCurrentID();
    }
    public Student(String firstName, String lastName, String email) {
        this(firstName, "",lastName,email);
    }

}
