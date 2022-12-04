package com.example.project1;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {

    private String course_name;
    private String course_code;
    private String offering_session;
    private String prerequisites;


    public Course() {
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

    public String getOffering_session() {
        return offering_session;
    }

    public void setOffering_session(String offering_session) {
        this.offering_session = offering_session;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }


}
