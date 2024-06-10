package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.command.Command;
import com.company.tracker.database.repository.impl.StudentRepositoryImpl;
import com.company.tracker.entity.Student;

import java.util.ResourceBundle;

public class List implements Command {
    private final ResourceBundle bundle;

    public List(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public String execute(String request) {

        StudentRepositoryImpl studentRepository = StudentRepositoryImpl.getInstance();
        java.util.List<Student> listOfStudents = studentRepository.getStudentsList();

        StringBuilder responseString = new StringBuilder();
        responseString.append("Students:\n");

        for (Student student : listOfStudents) {
            responseString.append(student.getId() + "\n");
        }
        return responseString.toString();
    }

}
