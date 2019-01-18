package com.example.aoneill98.organiser2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    public final static String DEFAULT="N/A";
    private static final String TAG = null;
    TextView tvCompanyHeader;
    ImageButton btnStatement, btnProducts, btnLocation, btnContactUs, btnContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tvCompanyHeader = findViewById(R.id.txtCompany);
        btnStatement=(ImageButton)findViewById(R.id.btnStatement);
        btnProducts=(ImageButton)findViewById(R.id.btnProducts);
        btnLocation=(ImageButton)findViewById(R.id.btnLocation);
        btnContactUs=(ImageButton)findViewById(R.id.btnContactUs);
        btnContacts=(ImageButton)findViewById(R.id.btnContacts);

        // Used to access the session variable 'company' which contains the company name of the user
        SharedPreferences sharedPreferences=getSharedPreferences("mydata", Context.MODE_PRIVATE);
        String company=sharedPreferences.getString("company", DEFAULT);

        // onClickListeners for each of the 6 imageButtons on the screen which take the user to the appropiate screen/class/xml layout
        btnStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Statements.class);
                startActivity(intent);
            }
        });

        btnProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Products.class);
                startActivity(intent);
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        btnContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, ContactUs.class);
                startActivity(intent);
            }
        });

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, Contacts.class);
                startActivity(intent);
            }
        });

        // sets the textview field at the top of the page to the company's name as stored in the session variable
        tvCompanyHeader.setText(company);

    }
}