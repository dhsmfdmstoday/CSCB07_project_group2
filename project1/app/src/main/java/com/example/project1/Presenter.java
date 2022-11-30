package com.example.project1;

import android.widget.Toast;

public class Presenter {
    private Model model;
    private LoginActivity login;
    private RegisterActivity register;

    public Presenter(Model model, LoginActivity view){
        this.model=model;
        this.login=view;
    }
    public Presenter(Model model, RegisterActivity register){
        this.model=model;
        this.register=register;
    }

    public void checkUsername(){
        String username= login.getUsername();
        login.displayMessage(username);
    }
    public void display(String message){
        login.displayMessage(message);
        Toast.makeText(login.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
    public void checkRegisteration(String []credentials){
        if (credentials[0].equals("")) {
            Toast.makeText(register.getApplicationContext(), "Username cannot be empty", Toast.LENGTH_LONG).show();
        }
        else if (credentials[1].equals("")) {
            Toast.makeText(register.getApplicationContext(), "Password cannot be empty", Toast.LENGTH_LONG).show();
        } else if (model.isUserFound(credentials[0]) == true) {
            Toast.makeText(register.getApplicationContext(), "Username already exists", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(register.getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();
            model.insertdataUser(credentials[0],credentials[1]);
            register.registerSucess();
        }
    }
    public void checkLogin(String []credentials) {
        if (credentials[0].equals("")) {
            display("Username cannot be empty");
        } else if (credentials[1].equals("")) {
            display("Password cannot be empty");
        } else if (model.isUserFound(credentials[0]) == false) {
            display("Username does not exists");
        } else if (model.isCorrectCred(credentials) == false) {
           display("Incorrect password");
        } else {
            Toast.makeText(login.getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();
            login.LoginSucess();
        }
    }
}
