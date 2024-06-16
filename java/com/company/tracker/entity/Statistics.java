package com.company.tracker.entity;

import java.util.*;

public class Statistics {

    public int getStudentId() {
        return studentId;
    }

    private int studentId;
    private Map<Course, Integer> stat;

    public Statistics(int studentId) {
        this.studentId = studentId;
        this.stat = new HashMap<>();
        stat.put(Course.JAVA, 0);
        stat.put(Course.DSA, 0);
        stat.put(Course.DATABASES, 0);
        stat.put(Course.SPRING, 0);
    }


    public Statistics(int studentId, Map<Course, Integer> stat) {
        this.studentId = studentId;
        this.stat = stat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistics that = (Statistics) o;

        if (studentId != that.studentId) return false;
        return Objects.equals(stat, that.stat);
    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + (stat != null ? stat.hashCode() : 0);
        return result;
    }

    public Map<Course, Integer> getStat() {
        return stat;
    }

    public boolean contains(Course course) {
        return stat.containsKey(course);
    }

    public Integer get(Course course) {
        return stat.get(course);
    }

    public List<Course> getActiveCourse() {
        List<Course> courses = new ArrayList<>();
        for (Map.Entry<Course, Integer> entry : stat.entrySet()) {
            if (entry.getValue() > 0) {
                courses.add(entry.getKey());
            }
        }
        return courses;
    }
}
