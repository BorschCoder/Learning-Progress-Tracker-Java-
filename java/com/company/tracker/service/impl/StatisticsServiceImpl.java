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
    public static final String RESPONSE_BUNDLE = "response";
    public static final String COURSE_BUNDLE = "course";


    private StatisticsServiceImpl() {
        this.bundleResponse = ResourceBundle.getBundle(RESPONSE_BUNDLE);
        this.bundleCourse = ResourceBundle.getBundle(COURSE_BUNDLE);
        this.studentRepository = StudentRepository.getInstance();
        this.generalStatisticRepository = GeneralStatisticRepository.getInstance();
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
    public Response getCategoryStatistic() {
        fillLeastMostPopCoursesMap();
        String responseString = String.format(bundleResponse.getString(ResponseType.GENERAL_STAT.name())
                , getMostPopCourse()
                , getLeastPopCourse()
                , getHighestActivityCourse()
                , getLowestActivityCourse()
                , getEasiestCourse()
                , getHardestCourse());

        return new Response(responseString);
    }

    @Override
    public Response getDetailsStatisticByCourse(String request) {
        Course course = CourseParser.parseCourse(request);
        if (course == null) {
            return new Response(ResponseType.UnknownCourse);
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
            return new String();
        } else {
            StringBuilder tableDetailStatistic = new StringBuilder();

            tableDetailStatistic.append(String.format(bundleResponse.getString(ResponseType.TABLE_HEADER.name())
                    , bundleCourse.getString(course.name())));
            for (Map.Entry<Integer, Integer> entry : sortPointsByCourse.entrySet()) {
                tableDetailStatistic.append(String.format(bundleResponse.getString(ResponseType.TABLE_CONTENT.name()),
                        entry.getKey()
                        , entry.getValue()
                        , calculateCompletionPercentage(entry.getValue(), pointsForCompletingCourse)));
            }
            return tableDetailStatistic.toString();
        }

    }

    private Map<Integer, Integer> getSortedPointsByCourse(Course course) {
        List<Student> sortListStudents = new ArrayList<>();

        Collections.copy(sortListStudents, studentRepository.findAll());
        sortListStudents.sort(new Comparator<Student>() {

            public int compare(Student first, Student second) {
                int firstPoint = first.getStatistics().getStat().get(course);
                int secondPoint = second.getStatistics().getStat().get(course);
                int pointResult = firstPoint - secondPoint;
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

    private Course getLeastPopCourse() {
        return courseList.get(courseList.size() - 1).getKey();
    }

    private Course getHighestActivityCourse() {
        List<StatisticRecord> ByJava = generalStatisticRepository.getStatisticRecordByCourse(JAVA);
        List<StatisticRecord> ByDSA = generalStatisticRepository.getStatisticRecordByCourse(DSA);
        List<StatisticRecord> BySpring = generalStatisticRepository.getStatisticRecordByCourse(SPRING);
        List<StatisticRecord> ByDatabases = generalStatisticRepository.getStatisticRecordByCourse(DATABASES);


        int highestActivity = Math.max(Math.max(ByJava.size(), ByDSA.size())
                , Math.max(BySpring.size(), ByDatabases.size()));
        if (highestActivity == ByJava.size()) {
            return JAVA;
        } else if (highestActivity == ByDSA.size()) {
            return DSA;
        } else if (highestActivity == BySpring.size()) {
            return SPRING;
        } else
            return DATABASES;
    }

    private Course getLowestActivityCourse() {
        List<StatisticRecord> ByJava = generalStatisticRepository.getStatisticRecordByCourse(JAVA);
        List<StatisticRecord> ByDSA = generalStatisticRepository.getStatisticRecordByCourse(DSA);
        List<StatisticRecord> BySpring = generalStatisticRepository.getStatisticRecordByCourse(SPRING);
        List<StatisticRecord> ByDatabases = generalStatisticRepository.getStatisticRecordByCourse(DATABASES);


        int lowestActivity = Math.min(Math.min(ByJava.size(), ByDSA.size())
                , Math.min(BySpring.size(), ByDatabases.size()));
        if (lowestActivity == ByJava.size()) {
            return JAVA;
        } else if (lowestActivity == ByDSA.size()) {
            return DSA;
        } else if (lowestActivity == BySpring.size()) {
            return SPRING;
        } else
            return DATABASES;
    }

}
