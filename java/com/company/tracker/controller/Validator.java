package com.company.tracker.controller;
import org.apache.commons.validator.routines.EmailValidator;

public class Validator {

    public static Boolean isValidFirstName(String firstName) {
        return null;
    }

    public static Boolean isValidLastName(String lastName) {
        return null;
    }

    public static Boolean isValidEmail(String email) {

        boolean valid = EmailValidator.getInstance().isValid(email);


        boolean allowLocal = true;
        //boolean valid = EmailValidator.getInstance(allowLocal).isValid(email);
        return valid;
    }

}
