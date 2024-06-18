package com.company.tracker.util;

import com.company.tracker.entity.Course;

public class CourseParser {
    public static Course parseCourse(String request) {
        try {
            return Course.valueOf(request.trim().toUpperCase());
        } catch (NullPointerException e) {
            return null;
        }
    }
}
