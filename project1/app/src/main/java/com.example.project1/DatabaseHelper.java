package com.example.project1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="register";
    public static final String COL_1="ID";
    public static final String COL_2="password";
    public static final String COL_3="admin";
    public static final String COL_4="course";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null,1);
    }
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID TEXT, Password TEXT, Admin TEXT, Course TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_1,"admin");
        contentValues.put(DatabaseHelper.COL_2,"admin");
        contentValues.put(DatabaseHelper.COL_3,"1");
        contentValues.put(DatabaseHelper.COL_4,"");
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
}