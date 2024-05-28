package com.company.tracker.validators;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentValidator {
    public static final String REGEX = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";

    public static boolean isValidName(String firstName) {
        Pattern pattern = Pattern.compile(REGEX);
        if (firstName == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(firstName);

        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {

        boolean valid = EmailValidator.getInstance().isValid(email);
        return valid;
    }

}
