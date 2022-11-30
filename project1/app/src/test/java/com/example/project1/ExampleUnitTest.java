package com.example.project1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    LoginActivity login;
    RegisterActivity register;
    @Mock
    Model model;
    
    @Test
    public void testPresenter(){
        when(login.getUsername()).thenReturn("abc");
        when(model.isUserFound("abc")).thenReturn(true);
        Presenter presenter = new Presenter(model,login);
        presenter.checkUsername();
        verify(login,times(1)).LoginSucess();
        InOrder order= inOrder(model,login);
        order.verify(model).isUserFound(anyString());
        order.verify(login).displayMessage("Username not found");

    }
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}