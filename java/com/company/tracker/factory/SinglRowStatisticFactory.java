package com.company.tracker.factory;

import com.company.tracker.database.repository.impl.GeneralStatisticRepository;
import com.company.tracker.entity.Course;
import com.company.tracker.entity.StatisticRecord;

public class SinglRowStatisticFactory {
    private static SinglRowStatisticFactory instance;
    private final GeneralStatisticRepository generalStatisticRepository;

    private SinglRowStatisticFactory() {
        generalStatisticRepository = GeneralStatisticRepository.getInstance();
    }

    public static SinglRowStatisticFactory getInstance() {
        if (instance == null) {
            instance = new SinglRowStatisticFactory();
        }
        return instance;
    }

    public void greateSinglRowStatistic(Course course, int assessment, int studentId) {
        StatisticRecord statisticRecord = new StatisticRecord(studentId, course, assessment);
        generalStatisticRepository.addStatisticRecords(statisticRecord);
    }
}
