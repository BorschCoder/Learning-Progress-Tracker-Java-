package com.company.tracker.database;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private static StudentRepository instance;

    private  StudentRepository() {
        students = new ArrayList<>();
    }
    public static StudentRepository getInstance(){
        if (instance == null){
            instance = new StudentRepository();
        }
        return instance;
    }

    private List<Student> students;
    public boolean add(Student student) {
        boolean added = students.add(student);
        return added;
    }

}
