package com.company.tracker.database.repository.impl;

import com.company.tracker.database.dao.StudentDAO;
import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.entity.Course;
import com.company.tracker.entity.Statistics;
import com.company.tracker.entity.Student;
import com.company.tracker.factory.DAOFactory;

import java.util.*;

public class StudentRepositoryImpl implements StudentRepository {

    public static final Student EMPTY_STUDENT = new Student();

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

    @Override
    public Student getEmptyStudent() {
        return EMPTY_STUDENT;
    }

    public boolean add(Student student) {
        return students.add(student);
    }

    public List<Student> getStudentsList() {
        return students;
    }

    public List<Statistics> getStatisticsAllStudents() {
        List<Statistics> statisticsList = new ArrayList<>();
        List<Student> listOfStudents = getStudentsList();

        for (Student student : listOfStudents) {
            statisticsList.add(student.getStatistics());
        }
        return statisticsList;
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    public Optional<Student> getStudentById(int id) {
        List<Student> listOfStudents = getStudentsList();
        for (Student student : listOfStudents) {
            if (student.getId() == id) {
                return Optional.of(student);
            }
        }
        return Optional.of(EMPTY_STUDENT);
    }

    @Override
    public void updateStatById(int id, Statistics newStat) {
        Student student = getStudentById(id).get();
        int index = students.indexOf(student);
        mergeStatistics(student, newStat);
        students.set(index, student);
    }

    private void mergeStatistics(Student student, Statistics anotherStat) {
        for (Map.Entry<Course, Integer> entry : student.getStatistics().getStat().entrySet()) {
            student.getStatistics().getStat().put(
                    entry.getKey(),
                    entry.getValue() + anotherStat.get(entry.getKey())
            );
        }
    }




}
