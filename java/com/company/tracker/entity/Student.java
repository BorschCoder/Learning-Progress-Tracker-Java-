package com.company.tracker.entity;

public class Student {

    private String firstName;
    private String secondName;
    private String lastName;
    private  String email;


    public Student(String firstName, String secondName, String lastName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
    }
    public Student(String firstName, String lastName, String email) {
        this(firstName, "",lastName,email);
    }
}
