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

    public void addCourse_admin(View v){

        startActivity(new Intent(AdminActivity.this,AdminAddCourse.class));
    }

    public void mdCourse_admin(View v){
        startActivity(new Intent(AdminActivity.this,AdminMDcourse.class));
    }

    public void loadTable(View v){
        createtable.makeTableAdmin(tableLayout);
    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin);
            courseModel= new CourseModel();
            createtable = new createTable(this);
            tableLayout = findViewById(R.id.table_admin);
        }

}