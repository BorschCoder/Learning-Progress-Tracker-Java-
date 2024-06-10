package com.company.tracker.database.dao.impl;

import com.company.tracker.database.dao.StudentDAO;
import com.company.tracker.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class DefaultStudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> findAll() {
        return new ArrayList<>();
    }
}
