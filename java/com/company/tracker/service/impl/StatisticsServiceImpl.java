package com.company.tracker.service.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.database.repository.impl.StudentRepositoryImpl;
import com.company.tracker.entity.Course;
import com.company.tracker.entity.Response;
import com.company.tracker.entity.Statistics;
import com.company.tracker.factory.RepositoryFactory;
import com.company.tracker.factory.StudentFactory;
import com.company.tracker.service.StatisticsService;
import com.company.tracker.service.StudentService;

import java.util.List;
import java.util.ResourceBundle;

public class StatisticsServiceImpl implements StatisticsService {
    private static StatisticsServiceImpl instance;
    private static StudentRepository studentRepository;
    public static final String RESPONSE_BUNDLE = "response";
    private final ResourceBundle bundle;
    private List<Statistics> statisticsList;

    private StatisticsServiceImpl() {
        this.bundle = ResourceBundle.getBundle(RESPONSE_BUNDLE);
        this.studentRepository = StudentRepositoryImpl.getInstance();
    }

    public static StatisticsServiceImpl getInstance() {
        if (instance == null) {
            instance = new StatisticsServiceImpl();
        }
        return instance;
    }

    public void updateGeneralStatistic() {
        statisticsList = studentRepository.getStatisticsAllStudents();
    }

    @Override
    public String getStatistics() {
        return null;
    }

    @Override
    public String getCourseStats(String request, Course course) {

        return null;
    }

    @Override
    public Response getGeneralStatistic() {
        String responseString = String.format(bundle.getString(ResponseType.GENERAL_STAT.name())
                , getMostPopGourse()
                , getLeastPopGourse()
                , getHighestActivityGourse()
                , getLowestActivityGourse()
                , getEasiestGourse()
                , getHardestGourse());
        return null;
    }

    private Course getEasiestGourse() {
        return null;
    }

    private Course getHardestGourse() {
        return null;
    }

    private Course getMostPopGourse() {
        return null;
    }

    private Course getLeastPopGourse() {
        return null;
    }

    private Course getHighestActivityGourse() {
        return null;
    }

    private Course getLowestActivityGourse() {
        return null;
    }

}
