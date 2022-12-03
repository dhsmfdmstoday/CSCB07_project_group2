package com.example.project1;

import android.os.Build;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CourseModel {

    private DatabaseReference courseRef;

    private String course_name;
    private String course_code;
    private List<Session> offering_session;
    private List<String> prerequisites;

    public CourseModel() {
        courseRef = FirebaseDatabase.getInstance().getReference("Courses");
    }

    public CourseModel(String course_name, String course_code, String offering_session,
                       String prerequisites) {
        this.course_name = course_name;
        this.course_code = course_code;
        this.offering_session = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
    }

    public void getCourse(Consumer<List<Course>> callback) {
        courseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Course> courses = new ArrayList<>();
                for (DataSnapshot CourseSnapshot: snapshot.getChildren()) {
                    Course course = CourseSnapshot.getValue(Course.class);
                    courses.add(course);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    callback.accept(courses);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void saveCourse(Course course, Consumer<Boolean> callback) {
        courseRef.child(course.getCourse_code()).setValue(course).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    callback.accept(task.isSuccessful());
                }
            }
        });
    }
}
