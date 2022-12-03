package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewAllCourses extends AppCompatActivity {
    private FirebaseAuth nFirebase = FirebaseAuth.getInstance();
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("project1");

    private Button back;
    //View all functionality not yet added;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses);
        // Initialize back button
        back = (Button) findViewById(R.id.back_btn);
        // Set up listener
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAllCourses.this, AdminSearchCourses.class));
            }
        });
    }
}