package com.example.project1;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Presenter {
    private Model model;
    private LoginActivity login;
    private RegisterActivity register;
    private FirebaseAuth nFirebaseAuth=FirebaseAuth.getInstance(); //firebase authentication
    private DatabaseReference mDataRef =FirebaseDatabase.getInstance().getReference("project1");; // real time database

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
        nFirebaseAuth.createUserWithEmailAndPassword(credentials[0], credentials[1]).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (!task.isSuccessful()){
                    try{
                        throw task.getException();
                    }
                    catch (FirebaseAuthWeakPasswordException weakPassword)
                    {
                        Toast.makeText(register.getApplicationContext(), "Password has to be at least 6 characters long", Toast.LENGTH_LONG).show();
                    }
                    catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                    {
                        Toast.makeText(register.getApplicationContext(), "Not a valid email address", Toast.LENGTH_LONG).show();
                    }
                    catch (FirebaseAuthUserCollisionException existEmail)
                    {
                        Toast.makeText(register.getApplicationContext(), "Username already exists", Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(register.getApplicationContext(), "Registration not completed", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(register.getApplicationContext(), "register successful", Toast.LENGTH_LONG).show();
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
    public void checkLogin(String []credentials) {
        nFirebaseAuth.signInWithEmailAndPassword(credentials[0], credentials[1]).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException weakPassword) {
                        display("Password has to be at least 6 characters long");
                    } catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                        display("Not a valid email address");
                    } catch (FirebaseAuthInvalidUserException noEmailExist) {
                        display("Username does not exists");
                    } catch (Exception e) {
                        display("Login not completed");
                    }
                } else {
                    Toast.makeText(login.getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();
                    login.LoginSucess();
                }
            }
        });


    }
}
