package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class AdminMDcourse extends AppCompatActivity implements Contract.modify {
    private CourseListAdapter courseListAdapter;

    public String checkFall(){
        CheckBox fall = findViewById(R.id.check_fall_md);

        if (fall.isChecked()){
            return ",Fall";
        }
        return "";
    }

    public String checkWinter(){
        CheckBox winter = findViewById(R.id.check_winter_md);
        if (winter.isChecked()){
            return ",Winter";
        }
        return "";
    }

    public String checkSummer(){
        CheckBox summer = findViewById(R.id.check_summer_md);
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
        EditText cod = findViewById(R.id.et_course_code_md);
        return cod.getText().toString().replaceAll("\\s+","");
    }
    public String getCourseName_admin(){
        EditText nam = findViewById(R.id.et_course_name_md);
        return nam.getText().toString();
    }
    public String getprerequiste_admin(){

        EditText pre = findViewById(R.id.et_course_pre_md);
        return pre.getText().toString().replaceAll("\\s+","");
    }

    public void editCourse(View v){
        String course [] ={getCourseCode_admin(), checkSemester(),getCourseName_admin(),getprerequiste_admin() };
        courseListAdapter.modifyCourse(course);
    }

    public void deleteAdminCourse(View v){
        String course [] ={getCourseCode_admin(), checkSemester(),getCourseName_admin(),getprerequiste_admin() };
        courseListAdapter.checkdeleteCourse(course);
    }

    public void toAdmin(View v){
        back();
    }

    public void back(){
        startActivity(new Intent(AdminMDcourse.this,AdminActivity.class));
    }

    public void setField(String name,String prereq){
        EditText pre = findViewById(R.id.et_course_pre_md);
        EditText nam = findViewById(R.id.et_course_name_md);
        nam.setText(name);
        pre.setText(prereq);
    }
    public void setOff(String [] offspring){
        CheckBox fall = findViewById(R.id.check_fall_md);
        CheckBox winter = findViewById(R.id.check_winter_md);
        CheckBox summer = findViewById(R.id.check_summer_md);
        for(int i=0;i<offspring.length;i++){
            if(offspring[i].equals("Fall")){
                fall.setChecked(true);
            }
            else if(offspring[i].equals("Winter")){
                winter.setChecked(true);
            }
            else if(offspring[i].equals("Summer")){
                summer.setChecked(true);
            }
        }
    }

    public void loadCourse(View v){
        courseListAdapter.getCoursedata(getCourseCode_admin());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_md_course);
        courseListAdapter = new CourseListAdapter(this);
    }
}