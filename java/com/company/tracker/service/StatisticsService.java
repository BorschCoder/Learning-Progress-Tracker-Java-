package com.company.tracker.service;

import com.company.tracker.entity.Course;
import com.company.tracker.entity.Response;

public interface StatisticsService {

    Response getCategoryStatistic();

    Response getDetailsStatisticByCourse(String request);

    void updateGeneralStatistic();


}
