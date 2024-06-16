package com.company.tracker.database.repository.impl;

import com.company.tracker.entity.StatisticRecord;
import com.company.tracker.entity.Student;
import com.company.tracker.factory.SinglRowStatisticFactory;

import java.util.List;

public class GeneralStatisticRepository {
    private static GeneralStatisticRepository instance;

    private GeneralStatisticRepository() {
    }

    public static GeneralStatisticRepository getInstance() {
        if (instance == null) {
            instance = new GeneralStatisticRepository();
        }
        return instance;
    }

    private List<StatisticRecord> statisticRecords;

    public List<StatisticRecord> getStatisticRecords() {
        return statisticRecords;
    }

    public void addStatisticRecords(StatisticRecord statisticRecord) {
        statisticRecords.add(statisticRecord);
    }
}
