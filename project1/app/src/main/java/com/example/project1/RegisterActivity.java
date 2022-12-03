package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements Contract.View {

    Model model;
    private Presenter presenter;
    private FirebaseAuth nFirebaseAuth=FirebaseAuth.getInstance(); //firebase authentication
    private DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference("project1");; // real time database

    public void displayMessage(String message){
        TextView textview= findViewById(R.id.text_register);
        textview.setText(message);
    }
    public String getUsername(){
        EditText editText= findViewById((R.id.et_email));
        return editText.getText().toString();
    }
    public String getPassword(){
        EditText editText = findViewById(R.id.et_password);
        return editText.getText().toString();
    }
    public String [] getCredentials(){
        String [] credentials = {getUsername(),getPassword()};
        return credentials;
    }
    public void backButton(View view){
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }

    public void createCred(View view){
        presenter.checkRegisteration(getCredentials());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter = new Presenter (new Model(),this);

    }

    public void registerSucess() {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}