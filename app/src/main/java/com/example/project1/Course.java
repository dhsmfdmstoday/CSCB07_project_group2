package com.example.project1;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {

    private String course_name;
    private String course_code;
    private List<Session> offering_session;
    private List<String> prerequisites;

    public Course() {
        this.offering_session = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
    }

    public Course(String course_name, String course_code, String offering_session, String prerequisite) {
        this.course_name = course_name;
        this.course_code = course_code;
        this.offering_session = new ArrayList<>();
        this.prerequisites = new ArrayList<>();

        offering_session = offering_session.toUpperCase();
        for (String offering_sessions: offering_session.split(",")) {
            switch (offering_sessions) {
                case "FALL":
                    this.offering_session.add(Session.Fall);
                    break;
                case "WINTER":
                    this.offering_session.add(Session.Winter);
                    break;
                case "SUMMER":
                    this.offering_session.add(Session.Summer);
                    break;
            }
        }
        prerequisite = prerequisite.toUpperCase();
        prerequisites = Arrays.asList(String.valueOf(prerequisite.split(",")));
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

    public List<Session> getOffering_session() {
        return offering_session;
    }

    public void setOffering_session(List<String> offering_session) {
        this.offering_session = new ArrayList<>();
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    @Override
    public boolean equals(Object o) {
        Course other = (Course) o;
        return this.course_code.equals(other.course_code);
    }

}
