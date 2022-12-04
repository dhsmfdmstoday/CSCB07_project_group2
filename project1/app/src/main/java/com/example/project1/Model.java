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
    List<String> password;
    List<String> Uid;
    List<String> completed;

    public Model(){
        mDataRef = FirebaseDatabase.getInstance().getReference("project1");
        users = new ArrayList<String>();
        password =new ArrayList<String>();
        Uid=new ArrayList<String>();
        completed=new ArrayList<String>();
        setUsers();
    }
    public void setUsers(){
        mDataRef.child(String.format("UserAccount")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserAccount user = snapshot.getValue(UserAccount.class);
                    String email = user.getEmailId();
                    users.add(email);
                    password.add(user.getPassword());
                    Uid.add(user.getUserToken());
                    completed.add(user.getCourses_completed());
                }
                Log.w("Mainactivity", "Email="+ users.toString()+password.toString()+Uid.toString()+completed.toString());
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
        return password.get(users.indexOf(credentials[0])).equals(credentials[1]);

    }

    public int getIndex(String email){
        return users.indexOf(email);
    }


}
