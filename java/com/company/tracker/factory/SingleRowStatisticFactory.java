package com.company.tracker.factory;

import com.company.tracker.database.repository.GeneralStatisticRepository;
import com.company.tracker.entity.Course;
import com.company.tracker.entity.StatisticRecord;

public class SingleRowStatisticFactory {
    private static SingleRowStatisticFactory instance;
    private final GeneralStatisticRepository generalStatisticRepository;

    private SingleRowStatisticFactory() {
        generalStatisticRepository = GeneralStatisticRepository.getInstance();
    }

    public static SingleRowStatisticFactory getInstance() {
        if (instance == null) {
            instance = new SingleRowStatisticFactory();
        }
        return instance;
    }

    public void createSingleRowStatistic(Course course, int assessment, int studentId) {
        StatisticRecord statisticRecord = new StatisticRecord(studentId, course, assessment);
        generalStatisticRepository.addStatisticRecords(statisticRecord);
    }
}
