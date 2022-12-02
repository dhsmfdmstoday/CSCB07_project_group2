package com.example.project1;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CourseModel {

    private String course_name;
    private String course_code;
    private List<String> offering_session;
    private List<String> prerequisites;

    public CourseModel(String course_name, String course_code,
                       List<String> offering_session, List<String> prerequisites) {
        this.course_name = course_name;
        this.course_code = course_code;
        this.offering_session = offering_session;
        this.prerequisites = prerequisites;
    }

    private FirebaseAuth nFirebaseAuth; //firebase authentication
    private DatabaseReference mDataRef; // real time database

    public CourseModel() {
        nFirebaseAuth = FirebaseAuth.getInstance();
        mDataRef = FirebaseDatabase.getInstance().getReference("project1");
    }

    public Course CreateCourse (String course_name, String course_code,
                                List<String> offering_session, List<String> prerequisites) {
        Course course = new Course();
        course.setCourse_name(course_name);
        course.setCourse_code(course_code);
        course.setOffering_session(offering_session);
        course.setPrerequisites(prerequisites);

        return course;
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                ", course_name='" + course_name + '\'' +
                ", course_code='" + course_code + '\'' +
                ", offering_session='" + offering_session + '\'' +
                ", prerequisites='" + prerequisites + '\'' +
                '}';
    }
}
