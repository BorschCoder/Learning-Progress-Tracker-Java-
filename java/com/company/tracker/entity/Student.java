package com.company.tracker.entity;

import com.company.tracker.util.IDGenerator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Student {

    private int id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private Statistics statistics;

    public Student() {
        this.firstName = "";
        this.secondName = "";
        this.lastName = "";
        this.email = "";
        this.statistics = new Statistics(id);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Student(String firstName, String secondName, String lastName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.email = email;
        this.statistics = new Statistics(id);
        this.id = IDGenerator.getCurrentID();
    }

    public Student(String firstName, String lastName, String email) {
        this(firstName, "", lastName, email);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        if (!firstName.equals(student.firstName)) return false;
        if (!secondName.equals(student.secondName)) return false;
        if (!lastName.equals(student.lastName)) return false;
        if (!email.equals(student.email)) return false;
        return statistics.equals(student.statistics);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + statistics.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", statistics=").append(statistics);
        sb.append('}');
        return sb.toString();
    }


}
