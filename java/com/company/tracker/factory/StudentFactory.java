package com.company.tracker.factory;

import com.company.tracker.entity.Student;
import com.company.tracker.entity.StudentCredential;

import java.util.Map;

import static com.company.tracker.entity.StudentCredential.*;

public final class StudentFactory {
    private static StudentFactory instance;

    private StudentFactory() {
    }

    public static StudentFactory getInstance() {
        if (instance == null) {
            instance = new StudentFactory();
        }
        return instance;
    }


    public Student createStudent(String firstName, String secondName, String lastName, String email) {
        return new Student(firstName, secondName, lastName, email);
    }
    public Student createStudent(Map<StudentCredential, String> studentInfo) {
        return studentInfo.containsKey(SECOND_NAME)?
                new Student(studentInfo.get(FIRST_NAME)
                        , studentInfo.get(SECOND_NAME)
                        , studentInfo.get(LAST_NAME)
                        , studentInfo.get(EMAIL))
                : new Student(studentInfo.get(FIRST_NAME)
                , studentInfo.get(LAST_NAME)
                , studentInfo.get(EMAIL));
    }


}
