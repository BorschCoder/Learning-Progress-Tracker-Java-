package com.company.tracker.service.impl;

import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.factory.RepositoryFactory;
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

import static com.company.tracker.entity.StudentCredential.*;


public class StudentServiceImpl implements StudentService {
    private static StudentServiceImpl instance;
    private StudentServiceImpl() {
        this.studentFactory = StudentFactory.getInstance();
        this.studentRepository = RepositoryFactory.createStudentRepository();
    }
    public static StudentServiceImpl getInstance(){
        if (instance == null){
            instance = new StudentServiceImpl();
        }
        return instance;
    }

    private static int countStudent = 0;
    private StudentFactory studentFactory;
    private StudentRepository studentRepository;

    public static int getCountStudent() {
        return countStudent;
    }

    @Override
    public ResponseType add(String request) {
        Map<StudentCredential, String> studentInfo = StudentParser.parseStudentInfo(request);

        if (studentInfo.isEmpty()) {
            return ResponseType.INCORRECT;
        }

        ResponseType responseType = isStudentValid(studentInfo);

        if (responseType != ResponseType.ADDED) {
            return responseType;
        }
        if (!UniqueCheck.isUniqueEmail(studentInfo.get(EMAIL))){
            return ResponseType.EMAIL_ALREADY_TAKEN;
        }
        Student student = studentFactory.createStudent(studentInfo);
        studentRepository.add(student);
        countStudent = countStudent +1;
        return responseType;
    }

    @Override
    public ResponseType add_points(String request) {
        return null;
    }


    private ResponseType isStudentValid(Map<StudentCredential, String> studentInfo) {

        if (studentInfo.containsKey(FIRST_NAME) && !StudentValidator.isValidName(studentInfo.get(FIRST_NAME))) {
            return ResponseType.INCORRECT_FIRST_NAME;
        }
        if (studentInfo.containsKey(SECOND_NAME) && !StudentValidator.isValidName(studentInfo.get(SECOND_NAME))) {
            return ResponseType.INCORRECT_SECOND_NAME;
        }
        if (studentInfo.containsKey(LAST_NAME) && !StudentValidator.isValidName(studentInfo.get(LAST_NAME))) {
            return ResponseType.INCORRECT_LAST_NAME;
        }
        if (studentInfo.containsKey(EMAIL) && !StudentValidator.isValidEmail(studentInfo.get(EMAIL))) {
            return ResponseType.INCORRECT_EMAIL;
        }
        return ResponseType.ADDED;

    }
}
