package com.example.project1;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CourseModel {

    private DatabaseReference courseRef= FirebaseDatabase.getInstance().getReference("Courses");;
    public List<String> course_code;
    public List<String> offering_session;
    public List<String> course_name;
    public List<String> prerequisites;


    public CourseModel() {
        this.course_code = new ArrayList<String>();
        this.offering_session = new ArrayList<String>();
        this.prerequisites = new ArrayList<String>();
        this.course_name = new ArrayList<String>();
        setCourse();
    }
    public void setCourse(){
        courseRef.child(String.format("Courses")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Course course = snapshot.getValue(Course.class);
                    String code= course.getCourse_code();
                    course_code.add(code);
                    String name= course.getCourse_name();
                    course_name.add(name);
                    String offering = course.getOffering_session();
                    offering_session.add(offering);
                    String pre = course.getPrerequisites();
                    prerequisites.add(pre);
                }
                Log.w("Course", "Course="+ course_code.toString()+ offering_session.toString()+prerequisites.toString()+course_name.toString());
          }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    public boolean isCourseFound(String course){
        return course_code.contains(course);
    }
    public boolean isPrerequisiteFound(String course){
        String[] s = course.split(",");
        for(int i =0;i<s.length;i++){
            if(!(isCourseFound(s[i]))){
                return false;
            }
        }
        return true;
    }
    public int sizeCourse(){
        return course_code.size();
    }
}
