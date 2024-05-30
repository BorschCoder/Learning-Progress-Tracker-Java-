package com.company.tracker.validators;

import com.company.tracker.entity.StudentCredential;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentValidator {
    public static final Pattern NAME_REGEX = Pattern.compile("[a-zA-Z]+([ '\\-]*[a-zA-Z]*)*");
    public static final Pattern NAME_REGEX_WRONT = Pattern.compile("(\\w*''\\w*)|(\\w*-'\\w*)|(\\w*'-\\w*)|(\\w*--\\w*)|('\\w)|(\\w+')|(-\\w)|(\\w+-)");

    public static Pattern EMAIL_REGEX = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        return !NAME_REGEX_WRONT.matcher(name).matches() && NAME_REGEX.matcher(name).matches() && name.length() > 1;

    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_REGEX.matcher(email);

        return matcher.matches();
    }

}
