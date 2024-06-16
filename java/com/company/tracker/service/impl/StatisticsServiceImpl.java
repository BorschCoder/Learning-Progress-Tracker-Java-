package com.company.tracker.service.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.database.repository.impl.StudentRepositoryImpl;
import com.company.tracker.entity.Course;
import com.company.tracker.entity.Response;
import com.company.tracker.entity.Statistics;
import com.company.tracker.service.StatisticsService;

import java.util.*;

public class StatisticsServiceImpl implements StatisticsService {
    private static StatisticsServiceImpl instance;

    private final StudentRepository studentRepository;
    public static final String RESPONSE_BUNDLE = "response";
    private final ResourceBundle bundle;
    private List<Statistics> statisticsList;
    private List<Map.Entry<Course, Integer>> courseList;


    private StatisticsServiceImpl() {
        this.bundle = ResourceBundle.getBundle(RESPONSE_BUNDLE);
        this.studentRepository = StudentRepositoryImpl.getInstance();
    }

    public static StatisticsServiceImpl getInstance() {
        if (instance == null) {
            instance = new StatisticsServiceImpl();
        }
        return instance;
    }

    public void updateGeneralStatistic() {
        statisticsList = studentRepository.getStatisticsAllStudents();
    }

    @Override
    public String getStatistics() {
        return null;
    }

    @Override
    public String getCourseStats(String request, Course course) {

        return null;
    }

    @Override
    public Response getCategoryStatistic() {
        fillLeastMostPopCoursesMap();

        String responseString = String.format(bundle.getString(ResponseType.GENERAL_STAT.name())
                , getMostPopCourse()
                , getLeastPopСourse()
                , getHighestActivityСourse()
                , getLowestActivityСourse()
                , getEasiestCourse()
                , getHardestCourse());
        return null;
    }

    private Course getEasiestCourse() {
        return null;
    }

    private Course getHardestCourse() {
        return null;
    }

    private Course getMostPopCourse() {
        return courseList.get(0).getKey();
    }

    private void fillLeastMostPopCoursesMap() {
        Map<Course, Integer> leastMostPopCourses = new TreeMap<Course, Integer>(Comparator.reverseOrder());
        for (Statistics statistics : this.statisticsList) {
            List<Course> activeCourse = statistics.getActiveCourse();

            for (Course course : activeCourse) {
                if (leastMostPopCourses.containsKey(course)) {
                    leastMostPopCourses.put(course, leastMostPopCourses.get(course) + 1);
                } else {
                    leastMostPopCourses.put(course, 1);
                }
            }
        }
        courseList = new ArrayList<>(leastMostPopCourses.entrySet());
        courseList.sort(Map.Entry.comparingByValue());

    }

    private Course getLeastPopСourse() {
        return courseList.get(courseList.size() - 1).getKey();
    }

    private Course getHighestActivityСourse() {
        return null;
    }

    private Course getLowestActivityСourse() {
        return null;
    }

}
