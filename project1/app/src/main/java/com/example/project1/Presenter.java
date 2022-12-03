package com.example.project1;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Presenter implements Contract.Presenter {
    private Model model;
    private LoginActivity login;
    private RegisterActivity register;
    private FirebaseAuth nFirebaseAuth; //firebase authentication
    private DatabaseReference mDataRef; // real time database

    public Presenter(Model model, LoginActivity view){
        this.model=model;
        this.login=view;
        nFirebaseAuth= FirebaseAuth.getInstance();
        mDataRef =FirebaseDatabase.getInstance().getReference("project1");
    }
    public Presenter(Model model, RegisterActivity register){
        this.model=model;
        this.register=register;
        nFirebaseAuth= FirebaseAuth.getInstance();
        mDataRef =FirebaseDatabase.getInstance().getReference("project1");

    }
    public Presenter(Model model, RegisterActivity register, LoginActivity activity){
        this.model=model;
        this.login=activity;
        this.register=register;
    }

    public void display_login(String message){
        login.displayMessage(message);
    }

    public void display_register(String message){
        register.displayMessage(message);
        Toast.makeText(register.getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    public void checkRegisteration(String []credentials){
        if(!(model.ValidEmail(credentials[0]))){
            display_register("Not a valid email address");
        }else
            if((model.isFoundEmail(credentials[0]))) {
            display_register("Username already exists");
        }
        else if(model.PassLength(credentials[1])) {
            display_register("Password has to be at least 6 characters long");
        }
        else{
            nFirebaseAuth.createUserWithEmailAndPassword(credentials[0], credentials[1]).addOnCompleteListener(register,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        display_register("register successful");
                        FirebaseUser firebaseUser = nFirebaseAuth.getCurrentUser();

                        UserAccount account = new UserAccount();

                        account.setEmailId(firebaseUser.getEmail());
                        account.setUserToken(firebaseUser.getUid());
                        account.setPassword(credentials[1]);
                        account.setCourses_completed("");
                        mDataRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                        register.registerSucess();

                    }
                }
            });
        }


    }
    public void checkLogin(String []credentials) {
        if (!(model.ValidEmail(credentials[0]))) {
            display_login("Not a valid email address");
        } else if (!(model.isFoundEmail(credentials[0]))) {
            display_login("Username does not exists");
        } else if (model.PassLength(credentials[1])) {
            display_login("Password has to be at least 6 characters long");
        } else if(!model.correctCred(credentials)){
              display_login("Invalid Password");
        }
        else{
            display_login("Login successful");
            login.LoginSucess(credentials[0]);
        }
    }
}


/*
  nFirebaseAuth.signInWithEmailAndPassword(credentials[0], credentials[1]).addOnCompleteListener(login, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if ((task.isSuccessful())) {
                            display_login("login successful");
                            login.LoginSucess(credentials[0]);
                        } else {
                            display_login("Invalid Password");
                        }

                    }
                });
            }
 */
