package com.company.tracker.util;

import com.company.tracker.entity.Course;

import java.util.HashMap;
import java.util.Map;

public class PointsParser {
    public static Map<Course, Integer> parsePointsRequest(String[] requestArray) {

        Map<Course, Integer> stat = new HashMap<>();
        if (requestArray.length != 5) {
            return stat;
        }

        stat.put(Course.JAVA, parseStringWithExc(requestArray[1]));
        stat.put(Course.DSA, parseStringWithExc(requestArray[2]));
        stat.put(Course.DATABASES, parseStringWithExc(requestArray[3]));
        stat.put(Course.SPRING, parseStringWithExc(requestArray[4]));

        return stat;
    }

    public static Integer parseStringWithExc(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
