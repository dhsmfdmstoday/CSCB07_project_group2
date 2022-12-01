package com.example.project1;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin {
    private FirebaseAuth nFirebaseAuth=FirebaseAuth.getInstance(); //firebase authentication
    private DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference("courses");; // real time database

}
