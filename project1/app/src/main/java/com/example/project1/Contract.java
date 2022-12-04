package com.example.project1;

import android.view.View;

public class Contract {
    public interface Model{
        public boolean isFoundEmail(String email);
    }

    public interface View{
        public String getUsername();
        public void displayMessage(String message);
        public String getPassword();
        public String[] getCredentials();
    }

    public interface Presenter{
        public void checkRegisteration(String [] credentials);
        public void checkLogin(String [] credentials);

    }
    public interface modify{
        public String checkFall();
        public String checkWinter();
        public String checkSummer();
        public String checkSemester();
        public String getCourseCode_admin();
        public String getCourseName_admin();
        public String getprerequiste_admin();
        public void back();
    }
}


