package com.example.project1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Model{
    Cursor cursor;
    SQLiteDatabase db;
    public Model(SQLiteDatabase db){
       this.db =db;
    }
    public ContentValues insertdata(String ID, String password,int admin){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_1,ID);
        contentValues.put(DatabaseHelper.COL_2,password);
        contentValues.put(DatabaseHelper.COL_3,admin);
        contentValues.put(DatabaseHelper.COL_4,"");
        return contentValues;
    }
    public void insertdataUser(String ID, String password){
        ContentValues cv = insertdata(ID,password,0);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, cv);
    }
    public void insertdataAdmin(String ID, String password){
        ContentValues cv = insertdata(ID,password,1);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, cv);
    }
    public boolean isUserFound(String username){
        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_1 + "=? ", new String[]{username});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                System.out.println("ad");
                return true;
            }
        }
        return false;
    }
    public boolean isCorrectCred(String []credentials) {
        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_1 + "=? AND " + DatabaseHelper.COL_2 + "=? ", credentials);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }
}
