package com.company.tracker.database;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    // TODO: 26.05.2024 just a collection that get from DAO 


    private List<Student> students;

    public StudentRepository() {

    }

    public ResponseType addStudent(String studentsData) {

        return ResponseType.ADDED;
    }
}
    /*public List<Student> findUsersByName(String name) {
//        List<Student> finded = new ArrayList<>();
//
//        for (Student item: students) {
//
//        }
   //

}
