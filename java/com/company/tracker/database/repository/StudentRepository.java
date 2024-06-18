package com.company.tracker.database.repository;

import com.company.tracker.database.repository.impl.GeneralStatisticRepositoryImpl;
import com.company.tracker.database.repository.impl.StudentRepositoryImpl;
import com.company.tracker.entity.Statistics;
import com.company.tracker.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    Student getEmptyStudent();


    boolean add(Student student);

    Optional<Student> getStudentById(int id);

    void updateStatById(int id, Statistics newStat);
    List<Statistics> getStatisticsAllStudents();
    List<Student>findAll();
    static StudentRepository getInstance() {
        return StudentRepositoryImpl.getInstance();
    }
}
