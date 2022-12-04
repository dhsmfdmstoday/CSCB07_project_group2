package com.example.project1;

import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class createTable {
    private AdminActivity adminActivity;
    private CourseModel courseModel;
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

    }
    public void tableGrid(TableLayout tablelayout) {
        GradientDrawable gd = gradientDrawable();
        System.out.println("qwef "+courseModel.offering_session.toString());
        System.out.println(courseModel.course_code.toString());
        TableRow tableRow = new TableRow(adminActivity);
        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        for (int i=0; i<courseModel.course_code.size();i++){
            TextView textView = new TextView(adminActivity);
            textView.setText(courseModel.course_code.get(i));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(15);
            textView.setBackground(gd);
            tableRow.addView(textView);
            textView.setText(courseModel.offering_session.get(i));
            tableRow.addView(textView);

            textView.setText(courseModel.prerequisites.get(i));
            tableRow.addView(textView);
            tablelayout.addView(tableRow);
        }


    }
}
