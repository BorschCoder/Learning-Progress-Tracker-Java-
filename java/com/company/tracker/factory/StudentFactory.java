package com.company.tracker.factory;

import com.company.tracker.entity.Student;

public final class StudentFactory {
    private static StudentFactory instance;

    private StudentFactory() {
    }

    public static StudentFactory getInstance() {
        if (instance == null) {
            instance = new StudentFactory();
        }
        return instance;
    }


    public Student createStudent(String firstName, String secondName, String lastName, String email) {
        return new Student(firstName, secondName, lastName, email);
    }

}
