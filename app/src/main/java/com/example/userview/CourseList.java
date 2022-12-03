package com.example.userview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CourseList extends AppCompatActivity {
    RecyclerView rV;
    ArrayList<OneCourse> list;
    DatabaseReference refer;
    static DatabaseReference ref;
    UserAdapter adapter;
    Button returnBtn;
    FirebaseAuth fbAuth;
    FirebaseUser user;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);

        refer = FirebaseDatabase.getInstance().getReference("project1");
        ref = refer;

        fbAuth = FirebaseAuth.getInstance();
        user = fbAuth.getCurrentUser();

//        userId = user.getEmail();
        userId = "c@c.com";

        list = new ArrayList<>();
        rV = findViewById(R.id.recycleView);
        rV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(this,list);
        rV.setAdapter(adapter);
        returnBtn = findViewById(R.id.exitBtn);

        returnBtn.setOnClickListener(view -> {
            Intent i = new Intent( CourseList.this, UserMainI.class);
            startActivity(i);
        });
        getCourseCode();
    }

    private void getCourseCode() {

        Query iQuery = refer.child("UserAccount").orderByChild("emailId").equalTo(userId);
        iQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dss : snapshot.getChildren()){
                    ref =dss.getRef().child("courses_completed").child("course");
                    ref.addValueEventListener(new ValueEventListener() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dss: snapshot.getChildren()){
                                OneCourse course = dss.getValue(OneCourse.class);
                                list.add(course);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}