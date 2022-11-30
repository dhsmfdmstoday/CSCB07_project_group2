package com.example.project1;

import java.util.List;

public class CourseModel {
    private int id;
    private String course_name;
    private String course_code;
    private List<String> offering_session;
    private List<String> prerequisites;

    public CourseModel(int id, String course_name, String course_code,
                       List<String> offering_session, List<String> prerequisites) {
        this.id = id;
        this.course_name = course_name;
        this.course_code = course_code;
        this.offering_session = offering_session;
        this.prerequisites = prerequisites;
    }

    public CourseModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public List<String> getOffering_session() {
        return offering_session;
    }

    public void setOffering_session(List<String> offering_session) {
        this.offering_session = offering_session;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "id=" + id +
                ", course_name='" + course_name + '\'' +
                ", course_code='" + course_code + '\'' +
                ", offering_session='" + offering_session + '\'' +
                ", prerequisites='" + prerequisites + '\'' +
                '}';
    }
}
