package com.company.tracker.entity;

import java.util.Map;

public class Statistics {

    private Map<Course, Integer> stat;

    public Map<Course, Integer> getStat() {
        return stat;
    }

    public boolean contains(Course course) {
        return stat.containsKey(course);
    }

    public Integer get(Course course) {
        return stat.get(course);
    }
}
