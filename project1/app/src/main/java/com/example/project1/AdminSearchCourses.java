package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class AdminSearchCourses extends AppCompatActivity {
    // Firebase Authentication and Reference
    private FirebaseAuth nFirebase = FirebaseAuth.getInstance();
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    // CourseModel to store information
    protected CourseModel model;
    //Button for listener and EditText to get value
    private Button searchBtn, viewAllBtn;
    private String courseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_courses);
        // Set views and cast to appropriate types
        viewAllBtn = (Button) findViewById(R.id.view_all);
        searchBtn = (Button) findViewById(R.id.search_btn);
        // Set listener
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On click, get string value of course code from user input
                courseCode = ((EditText)findViewById((R.id.courseCodeSearch))).getText().toString();
                // I am assuming we are storing courses on Firebase by course code as ID
                // You do so by running (reference to firebase).child(course_code).setValue(course_model);
                Query courseQuery = ref.orderByChild("course").equalTo(courseCode);
                // Add courseQuery listener
                courseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Make temporary variables to hold children values of datareference to course
                        String nameCourse = dataSnapshot.child("name").getValue().toString();
                        String codeCourse = dataSnapshot.child("code").getValue().toString();
                        List<String> sessionOffer = Arrays.asList(dataSnapshot.child("offering_sessions").getValue().toString().split(","));
                        List<String> preRequisites = Arrays.asList(dataSnapshot.child("prerequisites").getValue().toString().split(","));
                        // Make new course model to pass into AdminFunctions
                        model = new CourseModel(nameCourse, codeCourse, sessionOffer, preRequisites);
                        Intent i = new Intent(AdminSearchCourses.this, AdminFunctions.class);
                        i.putExtra("model_course", model);
                        // Since Query found a course with inputted course code,
                        // start new activity for AdminFunctions
                        startActivity(i);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(AdminSearchCourses.this, "Invalid Course Code", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminSearchCourses.this, ViewAllCourses.class));
            }
        });
        // No back button yet; can add it later but am unsure where it would go "back" to
    }
}