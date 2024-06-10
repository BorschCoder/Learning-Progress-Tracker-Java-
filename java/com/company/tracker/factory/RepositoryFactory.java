package com.company.tracker.factory;

import com.company.tracker.database.dao.StudentDAO;
import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.database.repository.impl.StudentRepositoryImpl;
import com.company.tracker.entity.Student;

import java.util.List;

public class RepositoryFactory {

    public static StudentRepository createStudentRepository() {
        return StudentRepositoryImpl.getInstance();
    }


}
