package com.company.tracker.factory;

import com.company.tracker.database.dao.StudentDAO;
import com.company.tracker.database.dao.impl.DefaultStudentDAOImpl;

public class DAOFactory {

    private static StudentDAO studentDAO = new DefaultStudentDAOImpl();

    public static StudentDAO getStudentDAO() {
        return studentDAO;
    }

}
