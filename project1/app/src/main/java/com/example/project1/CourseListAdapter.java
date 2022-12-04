package com.example.project1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CourseListAdapter  {
    public DatabaseReference mDataRef; // real time database
    private AdminAddCourse adminAddCourse;
    private AdminMDcourse adminMDcourse;
    private CourseModel courseModel;

    public CourseListAdapter(AdminMDcourse adminMDcourse) {
        mDataRef = FirebaseDatabase.getInstance().getReference("Courses");
        this.adminMDcourse = adminMDcourse;
        this.courseModel = new CourseModel();
    }

    public CourseListAdapter(AdminAddCourse adminAddCourse) {
        mDataRef = FirebaseDatabase.getInstance().getReference("Courses");
        this.adminAddCourse = adminAddCourse;
        this.courseModel = new CourseModel();

    }

    public void checkAddCourse(String [] course){
        if((courseModel.isCourseFound(course[0]))){
            Toast.makeText(adminAddCourse.getApplicationContext(), "Course already exists", Toast.LENGTH_LONG).show();
        }
        else if(!(course[3].equals(""))&&!courseModel.isPrerequisiteFound(course[3])){
            Toast.makeText(adminAddCourse.getApplicationContext(), "Invalid prerequisite", Toast.LENGTH_LONG).show();
        }
        else if((course[0]).equals("") || course[2].equals("") || course[1].equals("")){
            Toast.makeText(adminAddCourse.getApplicationContext(), "Course details are empty", Toast.LENGTH_LONG).show();
        }
        else{
            addAdminCourse(course);
            adminAddCourse.back();
        }
    }
    public void checkdeleteCourse(String [] course){
        if(!(courseModel.isCourseFound(course[0]))){
            Toast.makeText(adminMDcourse.getApplicationContext(), "Course does not exists", Toast.LENGTH_LONG).show();
        }
        else{
            deleteCourse(course);
            Toast.makeText(adminMDcourse.getApplicationContext(), "Course deleted", Toast.LENGTH_LONG).show();
            adminMDcourse.back();
        }
    }

    public void addAdminCourse(String [] course){
        Course c = new Course();
        c.setCourse_code(course[0]);
        c.setOffering_session(course[1]);
        c.setCourse_name(course[2]);
        c.setPrerequisites(course[3]);
        mDataRef.child("Courses").child(course[0]).setValue(c);
        Toast.makeText(adminAddCourse.getApplicationContext(), "Course Added", Toast.LENGTH_LONG).show();

    }

    public void modifyCourse(String[]course){
        if((course[0]).equals("")){
            Toast.makeText(adminMDcourse.getApplicationContext(), "Course code is empty", Toast.LENGTH_LONG).show();
        }
        else if(!(courseModel.isCourseFound(course[0]))){
            Toast.makeText(adminMDcourse.getApplicationContext(), "Course does not exists", Toast.LENGTH_LONG).show();
        }
        else if(!(course[3].equals(""))&&(!courseModel.isPrerequisiteFound(course[3]))){
            Toast.makeText(adminMDcourse.getApplicationContext(), "Invalid prerequisite", Toast.LENGTH_LONG).show();
        }
        else{
            modify(course);
            adminMDcourse.back();
        }
    }

    public void modify(String[]course){
        Course c = new Course();
        c.setCourse_code(course[0]);
        c.setOffering_session(course[1]);
        c.setCourse_name(course[2]);
        c.setPrerequisites(course[3]);
        mDataRef.child("Courses").child(course[0]).setValue(c);
        Toast.makeText(adminMDcourse.getApplicationContext(), "Course Modified", Toast.LENGTH_LONG).show();

    }

    public void deleteCourse(String[]course){
        mDataRef.child("Courses").child(course[0]).removeValue();
    }

    public void getCoursedata(String code){
        int i=courseModel.course_code.indexOf(code);
        if(i==-1){
            Toast.makeText(adminMDcourse.getApplicationContext(), "Course Not Found", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(adminMDcourse.getApplicationContext(), "Course Loaded", Toast.LENGTH_LONG).show();
            adminMDcourse.setField(courseModel.course_name.get(i),courseModel.prerequisites.get(i));
            adminMDcourse.setOff(courseModel.offering_session.get(i).split(","));

        }
    }


}
