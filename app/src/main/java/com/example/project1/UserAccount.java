package com.example.project1;

public class UserAccount {
    private String userToken;
    private String emailId;
    private String password;
    private String courses_completed;

    public UserAccount(){ }

    public void setUserToken(String userToken){
        this.userToken= userToken;
    }

    public String getUserToken(){
        return userToken;
    }

    public void setEmailId(String emailID){
        this.emailId= emailID;
    }

    public String getEmailId(){return emailId;}

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getCourses_completed(){
        return courses_completed;
    }

    public void setCourses_completed(String courses_completed){
        this.courses_completed=courses_completed;
    }

}
