package com.example.project1;

import android.content.Intent;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class UserAdapter {
    public DatabaseReference mDataRef; // real time database
    private CourseModel courseModel;
    private Model model;
    private String email;
    private createTable createtable;
    private UserActivity userActivity;

    public UserAdapter(UserActivity userActivity,String email){
        mDataRef = FirebaseDatabase.getInstance().getReference("project1").child("UserAccount");
        courseModel = new CourseModel();
        createtable = new createTable(userActivity);
        this.userActivity = userActivity;
        model = new Model();
        this.email=email;
    }

    public boolean checkCourse(String course,String []cpd){

        for(int i=0;i< cpd.length;i++){
            if(cpd[i].equals(course)){
                return true;
            }
        }
        return false;
    }

    public void modify(int i, String compltd){
        if(compltd.endsWith(",")){compltd=compltd.substring(0,compltd.length()-1);}
        mDataRef.child(model.Uid.get(i)).child("courses_completed").setValue(compltd);
        Toast.makeText(userActivity.getApplicationContext(), "Course Added", Toast.LENGTH_LONG).show();
    }

    public void AddCourseUser(String input){
        String compltd = model.completed.get(model.getIndex(email));
        String [] course = input.split(",");
        String [] cpd = compltd.split(",");
        Stack<String> todo = new Stack<String>();
        for(int i=0;i<course.length;i++){
            getPrereq(course[i],cpd,todo);
        }
       if (input.equals("")){
            Toast.makeText(userActivity.getApplicationContext(), "Course is empty", Toast.LENGTH_LONG).show();
        }
       else if(course.length==0){
           Toast.makeText(userActivity.getApplicationContext(), "Invalid Input", Toast.LENGTH_LONG).show();
       }
        else {
            for (int i = 0; i < course.length; i++) {
                if ((checkCourse(course[0],cpd))) {
                    Toast.makeText(userActivity.getApplicationContext(), "Already Completed: "+course[i], Toast.LENGTH_LONG).show();
                }
                else if(courseModel.course_code.indexOf(course[i]) ==-1){
                    Toast.makeText(userActivity.getApplicationContext(), "Invalid Course: "+course[i], Toast.LENGTH_LONG).show();

                }
                else if(!todo.isEmpty()){
                    Toast.makeText(userActivity.getApplicationContext(), "Please fulfill Prerequisite "+course[i], Toast.LENGTH_LONG).show();
                }

                else {
                    compltd = course[i]+ "," + compltd;
                    modify(model.getIndex(email), compltd);
                    userActivity.clear();
                }
            }
        }
    }


    public void Letsgo(TableLayout tableLayout,String input){
        Stack<String> todo = needCourse(input);
        ArrayList <String> a =generateTime(todo);
        createtable.makeTableTime(tableLayout,a);
        userActivity.clear();
    }

    public boolean meetPrereq(String course, String completed,int i){
        String [] cd = completed.split(",");
        int q = courseModel.course_code.indexOf(course);
        String a =courseModel.prerequisites.get(q);
        String b= courseModel.offering_session.get(q);
        String []c = a.split(",");
        String [] d =b.split(",");
        List<String> wordList = Arrays.asList(d);

        String e;
        if(i==0){e="Fall";}
        else if(i==1){e="Winter";}
        else{e="Summer";}
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(cd);
        for(int j=0; j<c.length;j++){
            if(!al.contains(c[j]) && !c[j].equals("") ){
                return false;
            }
        }
        if(wordList.contains(e)){return true;}
        return false;
    }
    public ArrayList<String> generateTime(Stack<String> toDo){
        String temp_completed;
        String completed = model.completed.get(model.getIndex(email));
        ArrayList<String> aaa = new ArrayList<String>();
        Stack <String> toDO_2 = new Stack<String>();

        while(!toDo.isEmpty() || !toDO_2.isEmpty()){
            for(int i =0;i<3;i++){
                String todo = "";
                temp_completed= completed;
                while(!toDo.isEmpty()) {
                    if(toDo.peek().equals("")){toDo.pop();}
                    if (meetPrereq(toDo.peek(), temp_completed,i)) {
                        todo = todo + "," + toDo.peek();
                        completed = completed + "," + toDo.peek();toDo.pop();
                    } else {
                        toDO_2.push(toDo.peek());

                        toDo.pop();
                    }
                }
                if(!todo.equals("")){todo=todo.substring(1);}
                aaa.add(todo);
                while(!toDO_2.isEmpty()){
                    toDo.push(toDO_2.pop());
                }
                if(toDo.isEmpty() && toDO_2.isEmpty()){break;}
            }
        }
        return aaa;


    }

    public Stack<String> needCourse(String input){
        Stack <String> toDo= new Stack<String>();
        String compltd = model.completed.get(model.getIndex(email));
        String [] course = input.split(",");
        String [] cpd = compltd.split(",");
        if (input.equals("")){
            Toast.makeText(userActivity.getApplicationContext(), "Course is empty", Toast.LENGTH_LONG).show();
        }
        else if(course.length==0){
            Toast.makeText(userActivity.getApplicationContext(), "Invalid Input", Toast.LENGTH_LONG).show();
        }
        else{
            for (int i = 0; i < course.length; i++) {
                if ((checkCourse(course[0],cpd))) {
                    Toast.makeText(userActivity.getApplicationContext(), "Already Completed: "+course[i], Toast.LENGTH_LONG).show();
                }
                else if(courseModel.course_code.indexOf(course[i]) ==-1){
                    Toast.makeText(userActivity.getApplicationContext(), "Invalid Course: "+course[i], Toast.LENGTH_LONG).show();

                }
                else {
                    toDo.push(course[i]);
                    getPrereq(course[i],cpd, toDo);
                }
            }


        }
        return toDo;
    }
    public String getPrereq(String wantTo, String [] haveDone,Stack <String> toDo){
        int a= courseModel.course_code.indexOf(wantTo);
        if(a==-1){
            return null;
        }
        String preq = courseModel.prerequisites.get(a);
        if(preq.equals("") || preq==null){return null;}
        String []pre = preq.split(",");
        for(int i =0;i<pre.length;i++){
            if(!checkCourse(pre[i],haveDone) && !toDo.contains(pre[i])){
                toDo.push(pre[i]);
                getPrereq(pre[i],haveDone, toDo);
            }
        }
        return null;
    }
}
