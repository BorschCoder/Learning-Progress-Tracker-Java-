package com.company.tracker.service;

import com.company.tracker.entity.Course;
import com.company.tracker.entity.Response;

public interface StatisticsService {
    public String getStatistics();
    public String getCourseStats(String request, Course course);
    public Response getGeneralStatistic();
    public void updateGeneralStatistic();

}
