package com.company.tracker.service.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.entity.*;
import com.company.tracker.factory.RepositoryFactory;
import com.company.tracker.factory.SingleRowStatisticFactory;
import com.company.tracker.factory.StudentFactory;
import com.company.tracker.service.StudentService;
import com.company.tracker.util.PointsParser;
import com.company.tracker.util.StudentParser;
import com.company.tracker.validators.PointsValidator;
import com.company.tracker.validators.StudentValidator;
import com.company.tracker.validators.UniqueCheck;

import java.util.Map;
import java.util.Optional;

import static com.company.tracker.controller.ResponseType.*;
import static com.company.tracker.entity.StudentCredential.EMAIL;


public class StudentServiceImpl implements StudentService {


    private static StudentServiceImpl instance;

    private StudentServiceImpl() {
        this.studentFactory = StudentFactory.getInstance();
        this.studentRepository = RepositoryFactory.createStudentRepository();
        this.singleRowStatisticFactory = SingleRowStatisticFactory.getInstance();
    }

    public static StudentServiceImpl getInstance() {
        if (instance == null) {
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    private static int countStudent = 0;
    private final StudentFactory studentFactory;
    private final StudentRepository studentRepository;
    private final SingleRowStatisticFactory singleRowStatisticFactory;

    public Response getStudentById(int id) {
        Optional<Student> foundStudent = studentRepository.getStudentById(id);
        return foundStudent.map(
                student -> new Response(ResponseType.SHOW_STATS, student.getId(), student.getStatistics().getStat())
                )
                .orElseGet(
                        () -> new Response(NO_STUDENTS_BY_ID)
                );
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

        Response response = getStudentById(PointsParser.parseStringWithExc(requestArray[0]));

        if (response.getType().equals(NO_STUDENTS_BY_ID)) {
            response.setStudentStringId(requestArray[0]);
            return response;
        }

        Map<Course, Integer> courseInfo = PointsParser.parsePointsRequest(requestArray);
        if (courseInfo.isEmpty() | !PointsValidator.isCorrectPointFormat(courseInfo)) {
            return new Response(INCORRECT_POINTS_FORMAT);
        }

        studentRepository.updateStatById(
                response.getStudentId(),
                new Statistics(response.getStudentId(), courseInfo)
        );

        recordSingleRowStatistic(courseInfo, response.getStudentId());

        return new Response(POINTS_UPDATED);
    }

    private void recordSingleRowStatistic(Map<Course, Integer> courseInfo, int studentId) {
        for (Map.Entry<Course, Integer> entry : courseInfo.entrySet()) {
            if (entry.getValue() == 0) {
                continue;
            }
            singleRowStatisticFactory.createSingleRowStatistic(entry.getKey(), entry.getValue(), studentId);
        }
    }


}
