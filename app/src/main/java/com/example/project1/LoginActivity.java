package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Presenter presenter;
    public void displayMessage(String message){
        TextView textview= findViewById(R.id.message_textview);
        textview.setText(message);
    }
    public String getUsername(){
        EditText editText= findViewById(R.id.et_id_login);
        return editText.getText().toString();
    }
    public String getPassword(){
        EditText editText = findViewById(R.id.et_password_login);
        return editText.getText().toString();
    }
    public String [] getCredentials(){
        String [] credentials = {getUsername(),getPassword()};
        return credentials;
    }
    public void checkLogin(View view){
         presenter.checkLogin(getCredentials());
    }
        public void LoginSucess(){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }

    public void handleClick(View view){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        presenter = new Presenter (new Model(),this);


    }
}