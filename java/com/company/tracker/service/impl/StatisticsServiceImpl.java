package com.company.tracker.service.impl;

import com.company.tracker.controller.ResponseType;
import com.company.tracker.database.repository.GeneralStatisticRepository;
import com.company.tracker.database.repository.StudentRepository;
import com.company.tracker.entity.*;
import com.company.tracker.service.StatisticsService;
import com.company.tracker.util.CourseParser;

import java.util.*;

import static com.company.tracker.entity.Course.*;

public class StatisticsServiceImpl implements StatisticsService {
    private static StatisticsServiceImpl instance;
    private final StudentRepository studentRepository;
    private final GeneralStatisticRepository generalStatisticRepository;
    private final ResourceBundle bundleResponse;
    private final ResourceBundle bundleCourse;
    private List<Map.Entry<Course, Integer>> courseList;
    private List<Statistics> statisticsList;
    private final List<Course> courses;
    public static final String RESPONSE_BUNDLE = "response";
    public static final String COURSE_BUNDLE = "course";


    private StatisticsServiceImpl() {
        this.bundleResponse = ResourceBundle.getBundle(RESPONSE_BUNDLE);
        this.bundleCourse = ResourceBundle.getBundle(COURSE_BUNDLE);
        this.studentRepository = StudentRepository.getInstance();
        this.generalStatisticRepository = GeneralStatisticRepository.getInstance();
        this.courses = Arrays.stream(values()).filter(c -> !c.equals(UNDEFINED)).toList();
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
    public Response getCategoryStatistic() {
        fillLeastMostPopCoursesMap();
        String responseString = String.format(bundleResponse.getString(ResponseType.GENERAL_STAT.name())
                , getMostPopCourses()
                , bundleCourse.getString(getLeastPopCourse().name())
                , getHighestActivityCourse()
                , getLowestActivityCourse()
                , bundleCourse.getString(getEasiestCourse().name())
                , bundleCourse.getString(getHardestCourse().name()));

        return new Response(responseString);
    }

    @Override
    public Response getDetailsStatisticByCourse(String request) {
        Course course = CourseParser.parseCourse(request);
        if (course == UNDEFINED) {
            return new Response(bundleResponse.getString(ResponseType.UnknownCourse.name()));
        }
        String tableDetailsStatistic = formDetailsStatistic(course);

        if (tableDetailsStatistic.isEmpty()) {
            return new Response(bundleCourse.getString(course.name()));
        }

        return new Response(tableDetailsStatistic);
    }

    private String formDetailsStatistic(Course course) {
        int pointsForCompletingCourse = generalStatisticRepository.getNumberOfPointsByCourse(course);
        Map<Integer, Integer> sortPointsByCourse = getSortedPointsByCourse(course);

        if (sortPointsByCourse.isEmpty()) {
            return String.format(bundleResponse.getString(ResponseType.TABLE_HEADER.name())
                    , bundleCourse.getString(course.name()));
        } else {
            StringBuilder tableDetailStatistic = new StringBuilder();

            tableDetailStatistic.append(String.format(bundleResponse.getString(ResponseType.TABLE_HEADER.name())
                    , bundleCourse.getString(course.name())));
            for (Map.Entry<Integer, Integer> entry : sortPointsByCourse.entrySet()) {
                if (entry.getValue() == 0) {
                    continue;
                }
                tableDetailStatistic.append(String.format(bundleResponse.getString(ResponseType.TABLE_CONTENT.name()),
                        entry.getKey()
                        , entry.getValue()
                        , calculateCompletionPercentage(entry.getValue(), pointsForCompletingCourse)));
            }
            return tableDetailStatistic.toString();
        }

    }

    private Map<Integer, Integer> getSortedPointsByCourse(Course course) {
        List<Student> sortListStudents = new ArrayList<>(studentRepository.findAll());
        sortListStudents.sort(new Comparator<Student>() {

            public int compare(Student first, Student second) {
                int firstPoint = first.getStatistics().getStat().get(course);
                int secondPoint = second.getStatistics().getStat().get(course);
                int pointResult = secondPoint - firstPoint;
                if (pointResult != 0) {
                    return pointResult;
                } else {
                    return first.getId() - second.getId();
                }
            }
        });

        Map<Integer, Integer> pointsByCourse = new LinkedHashMap<>();
        for (Student student : sortListStudents) {
            pointsByCourse.put(student.getId(), student.getStatistics().getStat().get(course));
        }
        return pointsByCourse;
    }

    private float calculateCompletionPercentage(int points, int pointsForCompletingCourse) {
        return (float) points / (float) pointsForCompletingCourse * 100;
    }

    private Course getEasiestCourse() {
        if (statisticsList.isEmpty()) {
            return UNDEFINED;
        }
        int allAssessmentByJava = generalStatisticRepository.getAverageAssessmentByCourse(JAVA);
        int allAssessmentByDSA = generalStatisticRepository.getAverageAssessmentByCourse(DSA);
        int allAssessmentBySpring = generalStatisticRepository.getAverageAssessmentByCourse(SPRING);
        int allAssessmentByDatabases = generalStatisticRepository.getAverageAssessmentByCourse(DATABASES);


        int biggerAssessment = Math.max(Math.max(allAssessmentByJava, allAssessmentByDSA)
                , Math.max(allAssessmentBySpring, allAssessmentByDatabases));

        if (biggerAssessment == allAssessmentByJava) {
            return JAVA;
        } else if (biggerAssessment == allAssessmentByDSA) {
            return DSA;
        } else if (biggerAssessment == allAssessmentBySpring) {
            return SPRING;
        } else
            return DATABASES;
    }

    private Course getHardestCourse() {

        if (statisticsList.isEmpty()) {
            return UNDEFINED;
        }

        int allAssessmentByJava = generalStatisticRepository.getAverageAssessmentByCourse(JAVA);
        int allAssessmentByDSA = generalStatisticRepository.getAverageAssessmentByCourse(DSA);
        int allAssessmentBySpring = generalStatisticRepository.getAverageAssessmentByCourse(SPRING);
        int allAssessmentByDatabases = generalStatisticRepository.getAverageAssessmentByCourse(DATABASES);

        int lowerAssessment = Math.min(Math.min(allAssessmentByJava, allAssessmentByDSA)
                , Math.min(allAssessmentBySpring, allAssessmentByDatabases));

        if (lowerAssessment == allAssessmentByJava) {
            return JAVA;
        } else if (lowerAssessment == allAssessmentByDSA) {
            return DSA;
        } else if (lowerAssessment == allAssessmentBySpring) {
            return SPRING;
        } else
            return DATABASES;
    }

    private String getMostPopCourses() {

        if (courseList.size() == 0) {
            return bundleCourse.getString(UNDEFINED.name());
        }

        StringBuilder sequenceMostPopCourses = new StringBuilder();
        for (int i = 0; i < courseList.size(); i++) {
            sequenceMostPopCourses.append(i != 0 ? ", " + bundleCourse.getString(courseList.get(i).getKey().name()) :
                    bundleCourse.getString(courseList.get(i).getKey().name()));
        }

        return sequenceMostPopCourses.toString();
    }

    private void fillLeastMostPopCoursesMap() {

        Map<Course, Integer> leastMostPopCourses = new TreeMap<Course, Integer>(Comparator.naturalOrder());
        updateGeneralStatistic();

        for (Course course : courses) {
            int countStudents = generalStatisticRepository.getCountStudentsOnCourse(course);

            if (countStudents != 0) {
                leastMostPopCourses.put(course, countStudents);
            }
        }

        courseList = new ArrayList<>(leastMostPopCourses.entrySet());
        courseList.sort(Map.Entry.comparingByValue());
    }


    private Course getLeastPopCourse() {

        if (courseList.isEmpty() ||
                courseList.get(0).getValue().equals(courseList.get(courseList.size() - 1).getValue())
        ) {
            return UNDEFINED;
        } else {
            return courseList.get(courseList.size() - 1).getKey();
        }

    }

    private String getHighestActivityCourse() {

        Map<Course, Integer> highestActivity = new TreeMap<>(Comparator.naturalOrder());

        for (Course course : courses) {
            int activity = generalStatisticRepository.getStatisticRecordByCourse(course).size();
            if (activity != 0) {
                highestActivity.put(course, activity);
            }
        }

        List<Map.Entry<Course, Integer>> courseActivityList = new ArrayList<>(highestActivity.entrySet());

        courseActivityList.sort(Map.Entry.comparingByValue());

        if (highestActivity.isEmpty()) {
            return bundleCourse.getString(UNDEFINED.name());
        } else {

            StringBuilder sequenceActivityCourses = new StringBuilder();
            for (int i = 0; i < courseActivityList.size(); i++) {
                sequenceActivityCourses.append(i != 0 ? ", " + bundleCourse.getString(courseActivityList.get(i).getKey().name()) :
                        bundleCourse.getString(courseActivityList.get(i).getKey().name()));
            }
            return sequenceActivityCourses.toString();
        }
    }

    private String getLowestActivityCourse() {
        Map<Course, Integer> mapActivity = new TreeMap<>(Comparator.naturalOrder());

        for (Course course : courses) {
            int activity = generalStatisticRepository.getStatisticRecordByCourse(course).size();
            if (activity != 0) {
                mapActivity.put(course, activity);
            }
        }

        List<Map.Entry<Course, Integer>> courseActivityList = new ArrayList<>(mapActivity.entrySet());

        courseActivityList.sort(Map.Entry.comparingByValue());

        if (mapActivity.isEmpty()
                || courseActivityList.get(0).getValue().equals(courseActivityList.get(courseActivityList.size() - 1).getValue())) {
            return bundleCourse.getString(UNDEFINED.name());
        } else {
            return bundleCourse.getString(courseActivityList.get(courseActivityList.size() - 1).getKey().name());
        }

    }

}
