package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdminActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private createTable createtable;
    private CourseModel courseModel;

    public GradientDrawable gradientDrawable(){
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(0x00000000); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFF000000);
        return gd;
    }


    public void makeTable(int count){
        GradientDrawable gd = gradientDrawable();
        tableLayout = (TableLayout) findViewById(R.id.table_admin);
        TableRow tableRow = new TableRow(this);
        for (int i = 0; i < 3; i++) {
            TextView textView = new TextView(this);
            if (i == 0) {
                textView.setText(courseModel.course_code.get(count));
            } else if (i == 1) {
                textView.setText(courseModel.offering_session.get(count));
            } else {
                textView.setText(courseModel.prerequisites.get(count));
            }
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(15);
            textView.setBackground(gd);
            tableRow.addView(textView);        // tableRow에 view 추가
        }
        tableLayout.addView(tableRow);        // tableLayout에 tableRow 추가
    }
    public void tableGrid() {
        int count =courseModel.course_code.size();
        System.out.println(count);
        tableLayout = (TableLayout) findViewById(R.id.table_admin);
        while (tableLayout.getChildCount() > 1)
            tableLayout.removeView(tableLayout.getChildAt(tableLayout.getChildCount() - 1));
        for(int i =0; i<count;i++){
            makeTable(i);
        }
    }

    public void addCourse_admin(View v){

        startActivity(new Intent(AdminActivity.this,AdminAddCourse.class));
    }

    public void mdCourse_admin(View v){
        startActivity(new Intent(AdminActivity.this,AdminMDcourse.class));
    }

    public void loadTable(View v){
        //createtable= new createTable(this);
        //createtable.makeTabe(tableLayout);
        System.out.println(courseModel.isCourseFound("CSCA00")+" asdf");
        System.out.println(courseModel.course_code.indexOf("CSCA00"));
        tableGrid();
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin);
            courseModel= new CourseModel();
        }

}