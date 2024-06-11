package com.company.tracker.database.repository.impl;

import com.company.tracker.database.dao.StudentDAO;
import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.entity.Student;
import com.company.tracker.factory.DAOFactory;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private static StudentRepositoryImpl instance;


    public static StudentRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new StudentRepositoryImpl();
        }
        return instance;
    }

    private final StudentDAO dao;

    private StudentRepositoryImpl() {
        this.dao = DAOFactory.getStudentDAO();
        this.students = dao.findAll();
    }

    private List<Student> students;

    public boolean add(Student student) {
        return students.add(student);
    }

    public List<Student> getStudentsList() {
        return students;
    }

    public Optional<Student> getStudentById(int id) {
        List<Student> listOfStudents = getStudentsList();
        for (Student student : listOfStudents) {
            if (student.getId() == id) {
                return Optional.of(student);
            }
        }
        return Optional.of(new Student());
    }


}
