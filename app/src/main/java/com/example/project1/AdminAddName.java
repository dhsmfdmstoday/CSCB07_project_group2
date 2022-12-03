package com.example.project1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.List;

public class AdminAddName extends AppCompatActivity {
    CourseModel Model;

    Button add_button;
    EditText CourseName, CourseCode, OfferingSessions, Prerequisites;
    ListView lst_course;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Model = new CourseModel();

        add_button = (Button) findViewById(R.id.add_button);
        CourseName = (EditText) findViewById(R.id.CourseName);
        CourseCode = (EditText) findViewById(R.id.CourseCode);
        OfferingSessions = (EditText) findViewById(R.id.OfferingSessions);
        Prerequisites = (EditText) findViewById(R.id.Prerequisites);
        lst_course = (ListView) findViewById(R.id.lst_course);

        CourseListAdapter adapter = new CourseListAdapter(this, R.layout.admin_add_course, lst_course);
        lst_course.setAdapter(adapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CourseModel Model = new CourseModel(CourseName.getText().toString(), CourseCode.getText().toString(),
                            OfferingSessions.getText().toString(), Prerequisites.getText().toString());
                    Toast.makeText(AdminAddName.this, "Successfully add the course", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(AdminAddName.this, "Failed to adding the course", Toast.LENGTH_LONG).show();
                }

                Course course = new Course();
                Model.getCourse((List<Course> courses) -> {
                    if (courses.contains(course)) {
                        Toast.makeText(AdminAddName.this, "Course has already existed", Toast.LENGTH_LONG).show();
                        return;
                    } else {

                        Model.saveCourse(course, (Boolean successful) -> {
                            if (successful) {
                                Toast.makeText(AdminAddName.this, "Successfully added", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(AdminAddName.this, "Failed to add", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });
    }
}
