package com.company.tracker.database.repository;

import com.company.tracker.entity.Student;

import java.util.Optional;

public interface StudentRepository {

    boolean add(Student student);

    Optional<Student> getStudentById(int id);

}
