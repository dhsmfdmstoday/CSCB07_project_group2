package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _login,_register;
    EditText _txtid, _txtPass;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db= openHelper.getReadableDatabase();
        _login = (Button)findViewById(R.id.Login);
        _register= (Button)findViewById(R.id.register_button);
        _txtid=(EditText)findViewById(R.id.et_id_login);
        _txtPass=(EditText)findViewById(R.id.et_password_login);
        _register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String ID= _txtid.getText().toString();
                String password = _txtPass.getText().toString();
                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME+" WHERE "+ DatabaseHelper.COL_1 + "=? AND " +DatabaseHelper.COL_2+ "=? ", new String[]{ID,password} );
                if(cursor != null){
                    if(cursor.getCount()>0){
                        Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "invalid username or password", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });


    }
}