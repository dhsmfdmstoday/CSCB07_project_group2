package com.example.project1;


import static org.mockito.Mockito.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith (MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    LoginActivity login;
    @Mock
    RegisterActivity register;
    @Mock
    Model model;

    @Mock
    FirebaseAuth nFireAuth;

    @Test
    public void testPresenter(){

        //check if input is received
        when(login.getUsername()).thenReturn("abc");
        when(!(model.ValidEmail("abc"))).thenReturn(true);

        //check if email is valid
        Presenter presenter = new Presenter(model,register,login);
        String[] cred = {"qqq@gmail.com",""};
        presenter.checkLogin(cred);
        verify(login).displayMessage("Not a valid email address");

        //check if not registered email is in the database
        cred[0]= "qqqasdf@gmail.com";
        when((model.ValidEmail("qqqasdf@gmail.com"))).thenReturn(true);
        presenter.checkLogin(cred);
        verify(login).displayMessage("Username does not exists");

        //check if password is length <6
        cred[0]="qqq@gmail.com";
        when((model.isFoundEmail("qqq@gmail.com"))).thenReturn(true);
        when((model.ValidEmail(("qqq@gmail.com")))).thenReturn(true);

        when((model.PassLength(""))).thenReturn((true));
        presenter.checkLogin(cred);
        verify(login).displayMessage("Password has to be at least 6 characters long");

        //incorrect password
        cred[1]="123123";
        when((model.PassLength("123123"))).thenReturn(false);
        presenter.checkLogin(cred);
        verify(login).displayMessage("Invalid Password");

        when((model.correctCred(cred))).thenReturn(true);
        presenter.checkLogin(cred);
        verify(login).displayMessage("Login successful");

    }
}