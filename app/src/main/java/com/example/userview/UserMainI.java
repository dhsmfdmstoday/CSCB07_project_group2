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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class UserMainI extends AppCompatActivity {

    Button addBtn, viewBtn, deleteBtn;
    EditText code;
    DatabaseReference refer;
    DatabaseReference ref;
    FirebaseAuth fbAuth;
    FirebaseUser user;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userview_main);

        addBtn = findViewById(R.id.addBtn);
        viewBtn = findViewById(R.id.viewBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        code = findViewById(R.id.code);
        refer = FirebaseDatabase.getInstance().getReference().child("project1");
        fbAuth = FirebaseAuth.getInstance();
        user = fbAuth.getCurrentUser();

//        userId = user.getEmail();
        userId = "c@c.com";

        addBtn.setOnClickListener(view -> AddCourse());

        viewBtn.setOnClickListener(view -> {
            startActivity(new Intent(UserMainI.this, CourseList.class));
            finish();
        });

        deleteBtn.setOnClickListener(view -> DeleteCourse());
    }

    private void DeleteCourse() {
        String courseCode = code.getText().toString();

        Query iQuery = refer.child("UserAccount").orderByChild("emailId").equalTo(userId);

        iQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        String list = (String) dss.child("courses_completed").child("codeString").getValue();
                        ref =dss.getRef().child("courses_completed");

                        if(!list.contains(",")){
                            if(list.equals(courseCode)) {
                                dss.getRef().child("courses_completed").child("codeString").setValue("");
                                Toast.makeText(UserMainI.this, "Deleted!", Toast
                                        .LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(UserMainI.this,
                                        "Course does not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            String[] li = list.split(", ");
                            if(list.contains(" "+courseCode+",") || li[0].equals(courseCode) || li[li.length - 1].equals(courseCode)){
                                String[] newLi = new
                                        String[li.length*2-3];
                                if(Objects.equals(li[0], courseCode)){
                                    newLi[0] = li[1];
                                    int j = 2;
                                    for(int k = 1; k<newLi.length-1;k++){
                                        if(j<li.length){
                                            newLi[k] = ", ";
                                            newLi[k + 1] = li[j];
                                            k++;
                                            j++;
                                        }else{
                                            break;
                                        }
                                    }
                                }else{
                                    newLi[0] = li[0];
                                    int j = 1;
                                    for(int i = 1; i < li.length*2-3; i++) {
                                        if (j < li.length) {
                                            if(!Objects.equals(li[j], courseCode)) {
                                                newLi[i] = ", ";
                                                newLi[i + 1] = li[j];
                                                i++;
                                            }else{
                                                i--;
                                            }
                                            j++;
                                        } else {
                                            break;
                                        }
                                    }
                                }
                                dss.getRef().child("courses_completed").child("codeString").setValue(String
                                        .join("",newLi));
                                Toast.makeText(UserMainI.this, "Deleted!", Toast
                                        .LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(UserMainI.this,
                                        "Course does not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        deleteForView(ref);
                    }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });


    }

    private void deleteForView(DatabaseReference ref) {
        String courseCode = code.getText().toString();
        Query iQuery = ref.child("course").orderByChild("code").equalTo(courseCode);

        iQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        if (dss.exists()) {
                            dss.getRef().removeValue();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void AddCourse() {
        String courseCode = code.getText().toString();
        Query iQuery = refer.child("UserAccount").orderByChild("emailId").equalTo(userId);

        iQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        if (dss.exists()) {
                            String list = (String) dss.child("courses_completed").child("codeString").getValue();
                            ref =dss.getRef().child("courses_completed");
                            if(!list.contains(",")){
                                if(list.equals(courseCode)){
                                    Toast.makeText(UserMainI.this,
                                            "Course Already Exists!", Toast.LENGTH_SHORT).show();
                                }else{
                                    if(list.equals("")){
                                        dss.getRef().child("courses_completed").child("codeString").setValue(courseCode);
                                    }else{
                                        dss.getRef().child("courses_completed")
                                                .child("codeString").setValue(list+", "+courseCode);
                                    }
                                    Toast.makeText(UserMainI.this
                                            , "Added!", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                String[] li = list.split(", ");
                                if(list.contains(" "+courseCode+",")){
                                    Toast.makeText(UserMainI.this
                                            , "Course Already Exists!", Toast.LENGTH_SHORT).show();
                                }else if(li[0].equals(courseCode) || li[li.length - 1]
                                        .equals(courseCode)){
                                        Toast.makeText(UserMainI.this,
                                                "Course Already Exists!", Toast.LENGTH_SHORT).show();
                                }else{
                                    String[] newLi = new String[li.length*2+1];
                                    newLi[li.length*2] = courseCode;
                                    newLi[li.length*2-1] = ", ";
                                    newLi[0] = li[0];
                                    int j = 1;
                                    for(int i = 1; i < li.length*2-2; i++) {
                                        if (j < li.length) {
                                            newLi[i] = ", ";
                                            newLi[i + 1] = li[j];
                                            j++;
                                            i++;
                                        } else {
                                            break;
                                        }
                                    }
                                    dss.getRef().child("courses_completed")
                                            .child("codeString").setValue(String.join("",newLi));
                                    Toast.makeText(UserMainI.this, "Added!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                            addForView(courseCode, ref);
                        }
                    }
                }else{

                    Toast.makeText(UserMainI.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }

        });
//        addForView(courseCode, ref);

    }

    private void addForView(String courseCode, DatabaseReference ref) {
        Query iQuery = ref.child("course").orderByChild("code").equalTo(courseCode);

        iQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    String id = ref.push().getKey();
                    OneCourse courseI = new OneCourse(courseCode);
                    if(id != null) {
                        ref.child("course").child(id).setValue(courseI);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}