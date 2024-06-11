package com.company.tracker.util;

import com.company.tracker.entity.Course;
import com.company.tracker.entity.Statistics;
import com.company.tracker.entity.StudentCredential;
import com.company.tracker.entity.StudentCredential.*;

import java.util.HashMap;
import java.util.Map;

import com.company.tracker.entity.Course.*;

public class PointsParser {
    public static Map<Course, Integer> parsePointsRequest(String[] requestArray) {

        Map<Course, Integer> stat = new HashMap<>();
        if (requestArray.length != 5) {
            return stat;
        }

        stat.put(Course.JAVA, Integer.valueOf(requestArray[1]));
        stat.put(Course.DSA, Integer.valueOf(requestArray[2]));
        stat.put(Course.DATABASES, Integer.valueOf(requestArray[3]));
        stat.put(Course.SPRING, Integer.valueOf(requestArray[4]));

        return stat;
    }
}
