package com.company.tracker.database.repository.impl;

import com.company.tracker.database.repository.GeneralStatisticRepository;
import com.company.tracker.entity.Course;
import com.company.tracker.entity.StatisticRecord;

import java.util.*;
import java.util.stream.Collectors;

public class GeneralStatisticRepositoryImpl implements GeneralStatisticRepository {
    private static GeneralStatisticRepositoryImpl instance;
    private final int NUMBER_OF_POINTS_BY_JAVA = 600;
    private final int NUMBER_OF_POINTS_BY_DSA = 400;
    private final int NUMBER_OF_POINTS_BY_DATABASES = 480;
    private final int NUMBER_OF_POINTS_BY_SPRING = 550;

    private GeneralStatisticRepositoryImpl() {
        this.statisticRecords = new ArrayList<>();
    }

    public static GeneralStatisticRepository getInstance() {
        if (instance == null) {
            instance = new GeneralStatisticRepositoryImpl();
        }
        return instance;
    }

    private List<StatisticRecord> statisticRecords;

    public List<StatisticRecord> getStatisticRecords() {
        return Collections.unmodifiableList(statisticRecords);
    }

    public void addStatisticRecords(StatisticRecord statisticRecord) {
        statisticRecords.add(statisticRecord);
    }

    public List<StatisticRecord> getStatisticRecordByCourse(Course course) {
        List<StatisticRecord> statisticRecordsByCourse = statisticRecords
                .stream()
                .filter(c -> c.getCourse()
                        .equals(course)).collect(Collectors.toList());

        return statisticRecordsByCourse;
    }

    public int getCountStudentsOnCourse(Course course) {

        Set<Integer> idStudents = new HashSet<>();
        List<StatisticRecord> statisticRecordsByCourse = getStatisticRecordByCourse(course);

        for (StatisticRecord statisticRecords : statisticRecordsByCourse) {
            idStudents.add(statisticRecords.getIdStudent());
        }

        return idStudents.size();
    }

    public int getAverageAssessmentByCourse(Course course) {
        List<StatisticRecord> statisticRecordsByCourse = getStatisticRecordByCourse(course);
        if (statisticRecordsByCourse.isEmpty()) {
            return 0;
        }

        int allAssessmentByCourse = 0;
        for (StatisticRecord statisticRecord : statisticRecordsByCourse) {
            allAssessmentByCourse += statisticRecord.getAssessment();
        }
        return allAssessmentByCourse / statisticRecordsByCourse.size();
    }


    public int getNumberOfPointsByCourse(Course course) {
        if (course.equals(Course.JAVA)) {
            return NUMBER_OF_POINTS_BY_JAVA;
        }
        if (course.equals(Course.DSA)) {
            return NUMBER_OF_POINTS_BY_DSA;
        }
        if (course.equals(Course.DATABASES)) {
            return NUMBER_OF_POINTS_BY_DATABASES;
        } else {
            return NUMBER_OF_POINTS_BY_SPRING;
        }

    }
}
