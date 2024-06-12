package com.company.tracker.entity;

import com.company.tracker.controller.ResponseType;

import java.util.Map;

public class Response {

    private ResponseType type;
    private Map<Course, Integer> statDTO;
    private int studentId;
    private String studentStringId;

    public Response(ResponseType type) {
        this(type, -1, null);
    }

    public Response(ResponseType type, String studentStringId) {
        this(type, -1, null);
        this.studentStringId = studentStringId;
    }

    public Response(ResponseType type, int studentId, Map<Course, Integer> statDTO) {
        this.type = type;
        this.studentId = studentId;
        this.statDTO = statDTO;
    }

    public ResponseType getType() {
        return type;
    }

    public Map<Course, Integer> getStatDTO() {
        return statDTO;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentStringId() {
        return studentStringId;
    }

    public void setStudentStringId(String studentStringId) {
        this.studentStringId = studentStringId;
    }
}
