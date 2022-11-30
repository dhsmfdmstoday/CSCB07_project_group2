package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _register;
    EditText _txtid, _txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openHelper = new DatabaseHelper(this);
        _register= (Button)findViewById(R.id.register);
        _txtid=(EditText)findViewById(R.id.et_id);
        _txtPass =(EditText) findViewById(R.id.et_password);
        _register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                db=openHelper.getWritableDatabase();
                String ID= _txtid.getText().toString();
                String password = _txtPass.getText().toString();
                insertdata(ID,password);
                Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void insertdata(String ID, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_1,ID);
        contentValues.put(DatabaseHelper.COL_2,password);
        contentValues.put(DatabaseHelper.COL_3,0);
        contentValues.put(DatabaseHelper.COL_4,"");
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}