package com.company.tracker.validators;

import com.company.tracker.entity.Course;
import com.company.tracker.entity.Student;
import com.company.tracker.service.EmailService;
import com.company.tracker.service.impl.EmailServiceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AntiSpamCheckerImpl {
    private static Map<Student, List<Course>> notifiedStudents;
    private static AntiSpamCheckerImpl instance;

    public AntiSpamCheckerImpl() {
        notifiedStudents = new HashMap<>();
    }

    public static AntiSpamCheckerImpl getInstance() {
        if (instance == null) {
            instance = new AntiSpamCheckerImpl();
        }
        return instance;
    }

    public boolean isFirstSending(Student student, Course course) {
        if (notifiedStudents.isEmpty()) {
            return true;
        }
        return notifiedStudents.get(student).contains(course);
    }


    public void addNotified(Student student, List<Course> courses) {
        notifiedStudents.put(student, courses);
    }


    public void cleanTheMapNotified() {
        notifiedStudents.clear();
    }
}
