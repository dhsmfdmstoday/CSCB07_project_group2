package com.example.project1;
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
}


