package com.company.tracker.database.repository;

import com.company.tracker.database.repository.impl.GeneralStatisticRepositoryImpl;
import com.company.tracker.entity.Course;
import com.company.tracker.entity.StatisticRecord;

import java.util.List;

public interface GeneralStatisticRepository {
    void addStatisticRecords(StatisticRecord statisticRecord);

    List<StatisticRecord> getStatisticRecordByCourse(Course course);

    static GeneralStatisticRepository getInstance() {
        return GeneralStatisticRepositoryImpl.getInstance();
    }

    int getAverageAssessmentByCourse(Course course);

    int getNumberOfPointsByCourse(Course course);
    int getCountStudentsOnCourse(Course course);
}
