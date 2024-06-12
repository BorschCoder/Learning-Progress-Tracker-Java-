package com.company.tracker.controller.command.impl;

import com.company.tracker.controller.command.Command;
import com.company.tracker.database.repository.impl.StudentRepositoryImpl;
import com.company.tracker.entity.Student;

import java.util.List;
import java.util.ResourceBundle;

import static com.company.tracker.controller.ResponseType.NO_STUDENTS_FOUND;

public class ListCommand implements Command {

    private static final String ITEM_STRING_PATTERN = "%d\n";
    private final ResourceBundle bundle;

    public ListCommand(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public String execute(String request) {

        StudentRepositoryImpl studentRepository = StudentRepositoryImpl.getInstance();
        List<Student> listCommandOfStudents = studentRepository.getStudentsList();
        if (listCommandOfStudents.isEmpty()){
            return  bundle.getString(NO_STUDENTS_FOUND.name());
        }
        StringBuilder responseString = new StringBuilder();
        responseString.append("Students:\n");

        for (Student student : listCommandOfStudents) {
            responseString.append(String.format(ITEM_STRING_PATTERN, student.getId()));
        }
        return responseString.toString();
    }

}
