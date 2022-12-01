package com.example.project1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;

public class AdminAddName extends AppCompatActivity {
    Button add_button;
    EditText CourseName, CourseCode, OfferingSessions, Prerequisites;
    ListView lst_course;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_button =  (Button) findViewById(R.id.add_button);
        CourseName = (EditText) findViewById(R.id.CourseName);
        CourseCode = (EditText) findViewById(R.id.CourseCode);
        OfferingSessions = (EditText) findViewById(R.id.OfferingSessions);
        Prerequisites = (EditText) findViewById(R.id.Prerequisites);
        lst_course = (ListView) findViewById(R.id.lst_course);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CourseModel CourseModel = new CourseModel(0, CourseName.getText().toString(), CourseCode.getText().toString(),
                            Collections.singletonList(OfferingSessions.getText().toString()),
                            Collections.singletonList(Prerequisites.getText().toString()));
                    Toast.makeText(AdminAddName.this, "Successfully add the course", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(AdminAddName.this, "Failed to adding the course", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
