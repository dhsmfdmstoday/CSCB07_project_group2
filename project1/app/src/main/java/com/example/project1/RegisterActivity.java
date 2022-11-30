package com.example.project1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Model model;
    private Presenter presenter;
    public String getUsername(){
        EditText editText= findViewById((R.id.et_id));
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
        openHelper = new DatabaseHelper(this);
        db=openHelper.getWritableDatabase();
        presenter = new Presenter (new Model(db),this);

    }

    public void registerSucess() {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}