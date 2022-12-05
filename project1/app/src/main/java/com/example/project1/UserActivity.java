package com.example.project1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class UserActivity extends AppCompatActivity {
    private UserAdapter userAdapter;
    public String ID;
    private EditText editText;
    private createTable createtable;
    private TableLayout tableLayout;

    public String coursename(){
        editText = findViewById(R.id.et_course_user);
        return editText.getText().toString().replaceAll("\\s+","");
    }

    public void clear() {
        editText = findViewById(R.id.et_course_user);
        editText.setText("");
    }
    public void Timetable(View v){
         userAdapter.Letsgo(tableLayout,coursename());
    }

    public void ViewCourseUser(View v){
        createtable.makeTableUser(tableLayout,ID);
        userAdapter=new UserAdapter(this,ID);
    }
    public void addCourseUser(View v){
        userAdapter.AddCourseUser(coursename());
        userAdapter=new UserAdapter(this,ID);
        createtable=new createTable(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Bundle extras = getIntent().getExtras();
        tableLayout = (TableLayout) findViewById(R.id.table_user);
        createtable = new createTable(this);
        if(extras !=null) {
             ID = extras.getString("id");
        }
        else{
            ID = "qqq@gmail.com";
        }
        userAdapter=new UserAdapter(this,ID);
    }
}