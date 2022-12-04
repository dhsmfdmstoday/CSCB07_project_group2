package com.example.project1;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Model implements Contract.Model {
    private FirebaseAuth nFirebaseAuth=FirebaseAuth.getInstance(); //firebase authentication
    private DatabaseReference mDataRef; // real time database
    List<String> users;
    String [] password;

    public Model(){
        mDataRef = FirebaseDatabase.getInstance().getReference("project1");
        users = new ArrayList<String>();
        password = new String[100];
        setUsers();
    }
    public void setUsers(){
        mDataRef.child(String.format("UserAccount")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserAccount user = snapshot.getValue(UserAccount.class);
                    String email = user.getEmailId();
                    users.add(email);
                    password[i]=user.getPassword();
                    i++;

                }
                Log.w("Mainactivity", "Email="+ users.toString());
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public boolean isFoundEmail(String email){
        return (users.contains(email));
    }
    public boolean PassLength(String password){
        return (password.length()<6);
    }
    public boolean ValidEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public boolean correctCred(String credentials[]){
        return password[users.indexOf(credentials[0])].equals(credentials[1]);

    }


}
