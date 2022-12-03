package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class AdminFunctions extends AppCompatActivity {
    private FirebaseAuth nFirebase = FirebaseAuth.getInstance();
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("project1");
    private CourseModel courseModel;

    private EditText courseName, courseCode, courseOffers, coursePreReq;
    private String newName, newCode, newOffers, newReq;

    private Button updateBtn, deleteBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_functions);
        // Get EditText values
        courseName = (EditText) findViewById(R.id.name_course);
        courseCode = (EditText) findViewById(R.id.code_course);
        courseOffers = (EditText) findViewById(R.id.offer_sessions);
        coursePreReq = (EditText) findViewById(R.id.pre_requisites);
        // Get Intent from AdminSearchCourses
        Intent i = getIntent();
        courseModel = (CourseModel)i.getSerializableExtra("model_course");
        // Set displayed text to previously searched course
        courseName.setText(courseModel.getCourseName());
        courseCode.setText(courseModel.getCourseCode());
        courseOffers.setText(courseModel.getOfferingSessions().toString());
        coursePreReq.setText(courseModel.getPreRequisites().toString());
        // Initialize buttons
        updateBtn = (Button) findViewById(R.id.update_btn);
        deleteBtn = (Button) findViewById(R.id.delete_btn);
        backBtn = (Button) findViewById(R.id.btn_back);
        // When Back Button is pressed...
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to AdminSearchCourses activity
                startActivity(new Intent(AdminFunctions.this, AdminSearchCourses.class));
            }
        });
        // When Delete Button is pressed...
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Query based on course name, again assuming courses are sorted by course code
                Query courseQuery = ref.orderByChild("course").equalTo(courseName.getText().toString());
                courseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Remove value at the given reference
                        snapshot.getRef().removeValue();
                        // Notify admin user that the course was deleted
                        Toast.makeText(AdminFunctions.this, "Course Deleted Successfully", Toast.LENGTH_LONG);
                        // Start course search activity again
                        startActivity(new Intent(AdminFunctions.this, AdminSearchCourses.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // If error, display course was note deleted message
                        Toast.makeText(AdminFunctions.this, "Course Was Not Deleted", Toast.LENGTH_LONG);
                    }
                });
            }
        });
        // When Update Button is pressed...
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get new specified fields
                newName = ((EditText)findViewById(R.id.name_course)).getText().toString();
                newCode = ((EditText)findViewById(R.id.code_course)).getText().toString();
                newOffers = ((EditText)findViewById(R.id.offer_sessions)).getText().toString();
                newReq = ((EditText)findViewById(R.id.pre_requisites)).getText().toString();

                try {
                    // If entered field is different from previous...
                    if (!newName.equals(courseName.getText().toString())) {
                        if(newName == "") throw new Exception(); // If new field is empty, throw exception
                        // Update previous name with new name
                        ref.child("course").child("name").setValue(newName);
                        courseName.setText(newName);
                    }
                    if (!newCode.equals(courseCode.getText().toString())) {
                        if(newCode == "") throw new Exception(); // If new field is empty, throw exception
                        // Update previous code with new code
                        ref.child("course").child("code").setValue(newCode);
                        courseCode.setText(newCode);
                    }
                    if (!newOffers.equals(courseOffers.getText().toString())) {
                        if(newOffers == "") throw new Exception(); // If new field is empty, throw exception
                        // Update previous offerings with new offerings
                        List <String> t = Arrays.asList(newOffers.toString().split(","));
                        ref.child("course").child("offering_sessions").setValue(t);
                        courseOffers.setText(newOffers);
                    }
                    if (!newReq.equals(coursePreReq.getText().toString())) {
                        if(newReq == "") throw new Exception(); // If new field is empty, throw exception
                        // Update previous prerequisites with new prerequisites
                        List <String> t = Arrays.asList(newReq.toString().split(","));
                        ref.child("course").child("prerequisites").setValue(t);
                        coursePreReq.setText(newReq);
                    }
                    // Display success message
                    Toast.makeText(AdminFunctions.this, "Course Updated Successfully", Toast.LENGTH_LONG);
                } catch (Exception e) {
                    // Display field cannot be empty message
                    Toast.makeText(AdminFunctions.this, "Field Cannot Be Empty", Toast.LENGTH_LONG);
                }
            }
        });
    }
}