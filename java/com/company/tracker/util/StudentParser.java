package com.company.tracker.util;

import com.company.tracker.entity.Student;
import com.company.tracker.entity.StudentCredential;

import java.util.HashMap;
import java.util.Map;

import static com.company.tracker.entity.StudentCredential.*;

public class StudentParser {
    public static final int LENGTH_WITH_SECOND_NAME = 4;
    public static final int LENGTH_WITHOUT_SECOND_NAME = 3;


    public static Map<StudentCredential, String> parseStudentInfo(String request) {

        Map<StudentCredential, String> credentials = new HashMap<StudentCredential, String>();

        String[] splitRequest = request.split(" ");
        if (splitRequest.length < LENGTH_WITHOUT_SECOND_NAME) {
            return credentials;
        }

        credentials.put(FIRST_NAME, splitRequest[0]);
        credentials.put(LAST_NAME, splitRequest[splitRequest.length - 2]);
        credentials.put(EMAIL, splitRequest[splitRequest.length - 1]);

        if (splitRequest.length == LENGTH_WITH_SECOND_NAME) {
            credentials.put(SECOND_NAME, getMiddleName(splitRequest));
            System.out.println(getMiddleName(splitRequest));
        }

        return credentials;
    }

    public static String getMiddleName(String[] parts) {
        String middleName = null;
        StringBuilder middleNameBuilder = new StringBuilder();
        for (int i = 1; i < parts.length - 1; i++) {
            if (middleNameBuilder.length() > 0) {
                middleNameBuilder.append(" ");
            }
            middleNameBuilder.append(parts[i]);
        }
        middleName = middleNameBuilder.toString();

        return middleName;
    }
}
