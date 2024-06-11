package com.company.tracker.service;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.entity.Response;

public interface StudentService {


    Response add(String request);

    Response addPoints(String request);

    Response getStudentById(int id);


}
