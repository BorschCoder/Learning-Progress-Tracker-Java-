package com.company.tracker.validators;

import com.company.tracker.entity.Course;

import java.util.Map;

public class PointsValidator {

    public static boolean isCorrectPointFormat(Map<Course, Integer> courseInfo) {
        if (courseInfo.isEmpty()) {
            return false;
        }
        for (Map.Entry<Course, Integer> entry : courseInfo.entrySet()) {
            Integer value = entry.getValue();
            if (value.equals(null) || value < 0) return false;
        }
        return true;
    }
}
