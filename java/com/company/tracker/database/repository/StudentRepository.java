package com.company.tracker.database.repository;

import com.company.tracker.entity.Statistics;
import com.company.tracker.entity.Student;

import java.util.Optional;

public interface StudentRepository {

    Student getEmptyStudent();


    boolean add(Student student);

    Optional<Student> getStudentById(int id);

    void updateStatById(int id, Statistics newStat);
}
