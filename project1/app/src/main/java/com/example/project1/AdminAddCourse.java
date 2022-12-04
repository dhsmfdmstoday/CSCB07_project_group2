package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.List;

public class AdminAddCourse extends AppCompatActivity implements Contract.modify {
    private CourseListAdapter courseListAdapter;

    public String checkFall(){
        CheckBox fall = findViewById(R.id.check_admin_fall);
        if (fall.isChecked()){
            return ",Fall";
        }
        return "";
    }

    public String checkWinter(){
        CheckBox winter = findViewById(R.id.check_admin_winter);
        if (winter.isChecked()){
            return ",Winter";
        }
        return "";
    }

    public String checkSummer(){
        CheckBox summer = findViewById(R.id.check_admin_winter);
        if (summer.isChecked()){
            return ",Summer";
        }
        return "";
    }

    public String checkSemester(){
        String sem = checkFall()+checkWinter()+checkSummer();
        if (sem.equals("")){
            return sem;
        }
        return sem.substring(1);
    }
    public String getCourseCode_admin(){
        EditText cod = findViewById(R.id.et_course_code);
        return cod.getText().toString().replaceAll("\\s+","");
    }
    public String getCourseName_admin(){
        EditText nam = findViewById(R.id.et_course_name);
        return nam.getText().toString();
    }
    public String getprerequiste_admin(){
        EditText pre = findViewById(R.id.et_course_pre);
        return pre.getText().toString().replaceAll("\\s+","");
    }
    public void addCourse(View v){
        String course [] ={getCourseCode_admin(), checkSemester(),getCourseName_admin(),getprerequiste_admin() };
        System.out.println(course.toString());
        courseListAdapter.checkAddCourse(course);
    }


    public void toAdmin(View v){
        back();
    }
    public void back(){
        startActivity(new Intent(AdminAddCourse.this,AdminActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_course);
        courseListAdapter= new CourseListAdapter(this);


    }


}
