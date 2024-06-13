package com.company.tracker.service;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.entity.Response;
import com.company.tracker.entity.Statistics;
import com.company.tracker.entity.Student;

import java.util.List;

public interface StudentService {


    Response add(String request);

    Response addPoints(String request);

    Response getStudentById(int id);

}
