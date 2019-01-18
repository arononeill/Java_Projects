package com.example.aoneill98.organiser2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    EditText txtCompany, txtPassword;
    TextView register;
    CardView login;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();

        login=(CardView) findViewById(R.id.cdLogin);
        register=(TextView) findViewById(R.id.etRegister);
        txtCompany=(EditText)findViewById(R.id.etCompany);
        txtPassword=(EditText)findViewById(R.id.etPassword);

        // If the user has clicked the login button the following code is carried out
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Company = txtCompany.getText().toString();
                String Password = txtPassword.getText().toString();

                // Selects what the user has entered in for company and password and uses them to check if the login table in the database contains such information
                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_2 + "=? AND " + DatabaseHelper.COL_4 + "=?", new String[]{Company, Password});

                // Checks to make sure the database table isn't empty
                if (cursor != null) {
                    if (cursor.getCount()>0) {
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();

                        // Stores the company name the user has entered as a session variable in order to be used across different screens
                        SharedPreferences sharedPreferences=getSharedPreferences("mydata", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor =sharedPreferences.edit();
                        editor.putString("company", txtCompany.getText().toString());
                        editor.commit();

                        // The app then moves screen to the main menu class and xml layout
                        Intent intent = new Intent(login.this, MainMenu.class);
                        startActivity(intent);
                    }

                    // Displays the following message if the database table is empty
                    else {
                        Toast.makeText(getApplicationContext(), "login failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // If the register button is clicked then the user is taken to the register class and xml layout in order to register their company
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}