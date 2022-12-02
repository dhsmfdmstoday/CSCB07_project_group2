package com.example.project1;

import java.util.List;

public class Course {

    private String course_name;
    private String course_code;
    private List<String> offering_session;
    private List<String> prerequisites;

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
}
