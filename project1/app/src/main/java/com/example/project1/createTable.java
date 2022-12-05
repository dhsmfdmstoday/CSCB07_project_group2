package com.example.project1;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class createTable {
    private AdminActivity adminActivity;
    private CourseModel courseModel;
    private UserActivity userActivity;
    private Model model;

    public GradientDrawable gradientDrawable(){
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(0x00000000); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFF000000);
        return gd;
    }


    public createTable(AdminActivity adminActivity){
        this.adminActivity=adminActivity;
        this.courseModel= new CourseModel();
        model = new Model();

    }

    public createTable(UserActivity userActivity){
        this.userActivity=userActivity;
        this.courseModel= new CourseModel();
        model = new Model();

    }

    public void timelineHeader(TableLayout tableLayout){
        GradientDrawable gd = gradientDrawable();
        TableRow tableRow = new TableRow(userActivity);
        for (int i = 0; i < 2; i++) {
            TextView textView = new TextView(userActivity);
            if (i == 0) {
                textView.setText("Session");
            } else {
                textView.setText(("Course"));
            }
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(15);
            textView.setBackgroundResource(R.color.grey);
            tableRow.addView(textView);
        }
        tableLayout.addView(tableRow);
    }

    public void makeTimeTable(TableLayout tableLayout, int year,String session,String course){
        GradientDrawable gd = gradientDrawable();
        TableRow tableRow = new TableRow(userActivity);
        for (int i = 0; i < 2; i++) {
            TextView textView = new TextView(userActivity);
            if (i == 0) {
                textView.setText(session+ " " +year);
            } else {
                textView.setText((course));
            }
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(15);
            textView.setBackground(gd);
            tableRow.addView(textView);
        }
        tableLayout.addView(tableRow);
    }


    public void courseHeader(TableLayout tableLayout,Activity activity){
        GradientDrawable gd = gradientDrawable();
        TableRow tableRow = new TableRow(activity);
        for (int i = 0; i < 3; i++) {
            TextView textView = new TextView(activity);
            if (i == 0) {
                textView.setText("Course");
            } else if (i == 1) {
                textView.setText(("Session Offerings"));
            } else {
                textView.setText(("Prerequisites"));
            }
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(15);
            textView.setBackgroundResource(R.color.grey);
            tableRow.addView(textView);
        }
        tableLayout.addView(tableRow);

    }
    public void makeTableTime(TableLayout tableLayout, ArrayList<String> todo){
        int year = 2022;
        int session = 2;
        clearTable(tableLayout);
        timelineHeader(tableLayout);
        for(int i=0;i<todo.size();i++){
            if(session==2){
                makeTimeTable(tableLayout,year,"Fall",todo.get(i));
            }else if(session==0){
                makeTimeTable(tableLayout,year,"Winter",todo.get(i));
            }else{
                makeTimeTable(tableLayout,year,"Summer",todo.get(i));
            }
            session++;
            if(session>2){
                session=session%3;
                year++;
            }
        }
    }

    public void makeTableAdmin(TableLayout tableLayout) {
        int count = courseModel.course_code.size();
        clearTable(tableLayout);
        courseHeader(tableLayout, adminActivity);
        for (int i = 0; i < count; i++) {
            drawTable(tableLayout, i, adminActivity);
        }
    }


    public void makeTableUser(TableLayout tableLayout,String email){
        int a =model.getIndex(email);
        String cpd =model.completed.get(a);
        String [] course = cpd.split(",");
        int count =courseModel.course_code.size();
        clearTable(tableLayout);
        courseHeader(tableLayout,userActivity);
        for(int j=0;j<course.length;j++) {
            int i =courseModel.course_code.indexOf(course[j]);
            if(i!=-1) {
                drawTable(tableLayout, courseModel.course_code.indexOf(course[j]), userActivity);
            }
        }

    }

    public void clearTable(TableLayout tableLayout){
        while (tableLayout.getChildCount() >= 1)
            tableLayout.removeView(tableLayout.getChildAt(tableLayout.getChildCount() - 1));

    }
    public void drawTable(TableLayout tableLayout, int count, Activity activity) {
        GradientDrawable gd = gradientDrawable();
        TableRow tableRow = new TableRow(activity);
        for (int i = 0; i < 3; i++) {
            TextView textView = new TextView(activity);
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
            tableRow.addView(textView);
        }
        tableLayout.addView(tableRow);

    }


}
