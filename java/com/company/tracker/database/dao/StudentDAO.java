package com.company.tracker.database.dao;

import com.company.tracker.entity.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> findAll();

}
