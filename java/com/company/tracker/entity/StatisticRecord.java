package com.company.tracker.entity;

public class StatisticRecord {
    private final int idStudent;
    private final Course course;
    private final int assessment;

    public StatisticRecord(int idStudent, Course course, int assessment) {
        this.idStudent = idStudent;
        this.course = course;
        this.assessment = assessment;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public Course getCourse() {
        return course;
    }

    public int getAssessment() {
        return assessment;
    }
}
