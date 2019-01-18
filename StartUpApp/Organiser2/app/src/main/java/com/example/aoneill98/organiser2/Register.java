package com.example.aoneill98.organiser2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    CardView btnReg;
    TextView btnLoginR;
    EditText etCompany, etOwner, etPassword, etEmail, etPhone, etLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper=new DatabaseHelper(this);

        btnReg = (CardView) findViewById(R.id.btnRegisterR);
        etCompany = (EditText)findViewById(R.id.etCompanyR);
        etOwner = (EditText)findViewById(R.id.etOwnerR);
        etPassword = (EditText)findViewById(R.id.etPasswordR);
        etLocation = (EditText)findViewById(R.id.etlocationR);
        etEmail = (EditText)findViewById(R.id.etEmailR);
        etPhone = (EditText)findViewById(R.id.etPhoneR);
        btnLoginR=(TextView) findViewById(R.id.btnLoginR);

        btnReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String company=etCompany.getText().toString();
                String owner=etOwner.getText().toString();
                String password=etPassword.getText().toString();
                String email=etEmail.getText().toString();
                String location=etLocation.getText().toString();
                String phone=etPhone.getText().toString();

                // Checks if the user has filled ut all fields and if they haven't, then they are displayed a message to do so
                if (company.isEmpty() || owner.isEmpty() || password.isEmpty() || email.isEmpty() || location.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill out all options",Toast.LENGTH_LONG).show();
                } else {
                    // The insertData function is called and the user's details are passed to it in order to enter the user into the database
                    insertdata(company, owner, password, email, location, phone);
                    Toast.makeText(getApplicationContext(), "register successfully",Toast.LENGTH_LONG).show();
                }
            }
        });

        // Checks whether the user has clicked that they've been already registered and sends them to the login page/class/xml layout if this is the case
        btnLoginR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, login.class);
                startActivity(intent);
            }
        });

    }

    // This function gets the details the user has enetered and puts them into the Users table in the database
    public void insertdata (String company, String owner, String password, String email, String location, String phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, company);
        contentValues.put(DatabaseHelper.COL_3, owner);
        contentValues.put(DatabaseHelper.COL_4, password);
        contentValues.put(DatabaseHelper.COL_5, email);
        contentValues.put(DatabaseHelper.COL_6, location);
        contentValues.put(DatabaseHelper.COL_7, phone);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}

