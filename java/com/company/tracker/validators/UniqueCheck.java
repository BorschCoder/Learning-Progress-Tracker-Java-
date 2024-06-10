package com.company.tracker.validators;

import com.company.tracker.database.repository.impl.StudentRepositoryImpl;
import com.company.tracker.entity.Student;
import java.util.List;

public class UniqueCheck {
    public static boolean isUniqueEmail(String email) {
        StudentRepositoryImpl studentRepository = StudentRepositoryImpl.getInstance();
        List <Student>listOfStudents = studentRepository.getStudentsList();
        for (Student student :listOfStudents ) {
            if (student.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }
}
