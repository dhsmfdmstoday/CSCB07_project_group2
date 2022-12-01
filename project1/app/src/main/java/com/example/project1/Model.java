package com.example.project1;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Model{
    private FirebaseAuth nFirebaseAuth; //firebase authentication
    private DatabaseReference mDataRef; // real time database

    public Model(){
        nFirebaseAuth = FirebaseAuth.getInstance();
        mDataRef = FirebaseDatabase.getInstance().getReference("project1");
    }

    public UserAccount createUserAccount(String []credentials,String Uid) {
        UserAccount account = new UserAccount();
        account.setEmailId(credentials[0]);
        account.setUserToken(Uid);
        account.setPassword(credentials[1]);
        account.setCourses_completed("");

        return account;

    }

}
