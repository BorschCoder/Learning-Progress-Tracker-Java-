package com.company.tracker.service.impl;

import com.company.tracker.database.repository.GeneralStatisticRepository;
import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.entity.Course;
import com.company.tracker.entity.Response;
import com.company.tracker.entity.Student;
import com.company.tracker.service.EmailService;
import com.company.tracker.validators.AntiSpamCheckerImpl;

import java.util.*;

import static com.company.tracker.controller.ResponseType.*;
import static com.company.tracker.entity.Course.UNDEFINED;
import static com.company.tracker.entity.Course.values;

public class EmailServiceImp implements EmailService {
    public EmailServiceImp() {
        this.bundleResponse = ResourceBundle.getBundle(RESPONSE_BUNDLE);
        this.studentRepository = StudentRepository.getInstance();
        this.generalStatisticRepository = GeneralStatisticRepository.getInstance();
        this.bundleCourse = ResourceBundle.getBundle(COURSE_BUNDLE);
        this.antiSpamChecker = AntiSpamCheckerImpl.getInstance();
    }

    public static EmailService getInstance() {
        if (instance == null) {
            instance = new EmailServiceImp();
        }
        return instance;
    }

    private static EmailService instance;
    private StudentRepository studentRepository;
    private final ResourceBundle bundleResponse;
    private final ResourceBundle bundleCourse;
    private final GeneralStatisticRepository generalStatisticRepository;
    private final AntiSpamCheckerImpl antiSpamChecker;
    public static final String RESPONSE_BUNDLE = "response";
    public static final String COURSE_BUNDLE = "course";

    @Override
    public Response notifyCommand() {
        Map<Student, List<Course>> graduatedStudents = getGraduatedStudents();
        int countNotifiedStudents = 0;
        if (graduatedStudents.isEmpty()) {
            return new Response(String.format(bundleResponse.getString(NOTIFIED_COUNT.name()), countNotifiedStudents));
        } else {
            StringBuilder responseString = new StringBuilder();
            for (Map.Entry<Student, List<Course>> entry : graduatedStudents.entrySet()) {
                boolean notified = false;
                for (Course course : entry.getValue()) {
                    if (!antiSpamChecker.isFirstSending(entry.getKey(), course)) {
                        continue;
                    }
                    responseString.append(String.format(bundleResponse.getString(RECIPIENT_STUDENT.name())
                            , entry.getKey().getEmail()));

                    responseString.append(bundleResponse.getString(SENDER.name()));

                    responseString.append(String.format(bundleResponse.getString(EMAIL_TEXT.name())
                            , entry.getKey().getFullName(), bundleCourse.getString(course.name())));
                    notified = true;

                }
                if (notified) {
                    antiSpamChecker.addNotified(entry.getKey(), entry.getValue());
                    countNotifiedStudents += 1;
                }
            }
            responseString.append(String.format(bundleResponse.getString(NOTIFIED_COUNT.name())
                    , countNotifiedStudents));

            return new Response(responseString.toString());
        }

    }

    private Map<Student, List<Course>> getGraduatedStudents() {
        Map<Student, List<Course>> graduatedStudents = new HashMap<>();
        List<Student> studentList = studentRepository.findAll();
        Map<Course, Integer> pointsForCompletingCourse = getPointsForCompletingCourse();

        for (Student student : studentList) {
            List<Course> coursesCompleted = new ArrayList<>();
            Map<Course, Integer> statistics = student.getStatistics().getStat();

            for (Map.Entry<Course, Integer> entry : statistics.entrySet()) {
                if (entry.getValue().equals(pointsForCompletingCourse.get(entry.getKey()))) {
                    coursesCompleted.add(entry.getKey());
                }
            }
            if (!coursesCompleted.isEmpty()) {
                graduatedStudents.put(student, coursesCompleted);
            }
        }
        return graduatedStudents;
    }

    private Map<Course, Integer> getPointsForCompletingCourse() {

        Map<Course, Integer> pointsForCompletingCourse = new HashMap<>();
        List<Course> courses = Arrays.stream(values()).filter(c -> !c.equals(UNDEFINED)).toList();

        for (Course course : courses) {
            pointsForCompletingCourse.put(course, generalStatisticRepository.getNumberOfPointsByCourse(course));
        }
        return pointsForCompletingCourse;
    }

}
