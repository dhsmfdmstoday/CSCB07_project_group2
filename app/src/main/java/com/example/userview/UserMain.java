package com.example.userview;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserMain extends AppCompatActivity {

    Button addBtn, viewBtn, deleteBtn;
    EditText code;
    DatabaseReference refer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addBtn = findViewById(R.id.addBtn);
        viewBtn = findViewById(R.id.viewBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        code = findViewById(R.id.code);
        refer = FirebaseDatabase.getInstance().getReference().child("project1");

        addBtn.setOnClickListener(view -> AddCourse());

        viewBtn.setOnClickListener(view -> {
            startActivity(new Intent(UserMain.this, CourseList.class));
            finish();
        });

        deleteBtn.setOnClickListener(view -> DeleteCourse());
    }

    private void DeleteCourse() {
        String courseCode = code.getText().toString();
        Query iQuery = refer.child("course").orderByChild("code").equalTo(courseCode);

        iQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        if (dss.exists()) {
                            dss.getRef().removeValue();
                            Toast.makeText(UserMain.this, "Deleted!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(UserMain.this, "Course does not exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

    private void AddCourse() {
        String courseCode = code.getText().toString();
        Query iQuery = refer.child("course").orderByChild("code").equalTo(courseCode);

        iQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Toast.makeText(UserMain.this, "Course already exists!", Toast.LENGTH_SHORT).show();
                }else{
                    String id = refer.push().getKey();
                    OneCourse course = new OneCourse(courseCode);
                    if(id != null) {
                        refer.child("course").child(id).setValue(course)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(UserMain.this, "Added!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(UserMain.this, "Not added!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else{
                        Toast.makeText(UserMain.this, "Not added!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }
}