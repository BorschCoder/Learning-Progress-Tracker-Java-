package com.company.tracker.service.impl;

import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.entity.Course;
import com.company.tracker.entity.Response;
import com.company.tracker.factory.RepositoryFactory;
import com.company.tracker.util.PointsParser;
import com.company.tracker.util.StudentParser;
import com.company.tracker.controller.ResponseType;
import com.company.tracker.database.repository.impl.StudentRepositoryImpl;
import com.company.tracker.entity.Student;
import com.company.tracker.entity.StudentCredential;
import com.company.tracker.factory.StudentFactory;
import com.company.tracker.service.StudentService;
import com.company.tracker.validators.StudentValidator;
import com.company.tracker.validators.UniqueCheck;

import java.util.Map;

import static com.company.tracker.controller.ResponseType.*;
import static com.company.tracker.entity.StudentCredential.*;


public class StudentServiceImpl implements StudentService {
    private static StudentServiceImpl instance;

    private StudentServiceImpl() {
        this.studentFactory = StudentFactory.getInstance();
        this.studentRepository = RepositoryFactory.createStudentRepository();
    }

    public static StudentServiceImpl getInstance() {
        if (instance == null) {
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    private static int countStudent = 0;
    private StudentFactory studentFactory;
    private StudentRepository studentRepository;

    public Response getStudentById(int id) {
        Student find = studentRepository.getStudentById(id).get();
        return new Response(ResponseType.SHOW_STATS, find.getId(), find.getStatistics().getStat());
    }

    public static int getCountStudent() {
        return countStudent;
    }

    @Override
    public Response add(String request) {
        Map<StudentCredential, String> studentInfo = StudentParser.parseStudentInfo(request);

        if (studentInfo.isEmpty()) {
            return new Response(INCORRECT);
        }

        ResponseType responseType = StudentValidator.isStudentValid(studentInfo);

        if (responseType != ADDED) {
            return new Response(responseType);
        }
        if (!UniqueCheck.isUniqueEmail(studentInfo.get(EMAIL))) {
            return new Response(EMAIL_ALREADY_TAKEN);
        }
        Student student = studentFactory.createStudent(studentInfo);
        studentRepository.add(student);
        countStudent = countStudent + 1;
        return new Response(responseType);
    }

    @Override
    public Response addPoints(String request) {
        String[] requestArray = request.split(" ");
        Map<Course, Integer> courseInfo = PointsParser.parsePointsRequest(requestArray);

        if (courseInfo.isEmpty()) {
            return new Response(INCORRECT);
        }
        return null;
    }


}
