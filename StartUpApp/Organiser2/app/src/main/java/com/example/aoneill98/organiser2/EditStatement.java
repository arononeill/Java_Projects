package com.example.aoneill98.organiser2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditStatement extends AppCompatActivity {

    TextView etIdAdd2, etCustomerAdd2, etOrderAdd2, etQuantityAdd2, etPriceAdd2, etTotalAdd2;
    public final static String DEFAULT="N/A";
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_statement);
        getTextView();

        // Used to access session variables
        SharedPreferences sharedPreferences=getSharedPreferences("mydata", Context.MODE_PRIVATE);
        final String company=sharedPreferences.getString("company", DEFAULT);

        final DatabaseHelper openHelper = new DatabaseHelper(this);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etId = etIdAdd2.getText().toString();
                String etCustomer = etCustomerAdd2.getText().toString();
                String etOrder = etOrderAdd2.getText().toString();
                String etQuantity = etQuantityAdd2.getText().toString();
                String etPrice = etPriceAdd2.getText().toString();
                String etTotal = etTotalAdd2.getText().toString();

                // Checks if the user has filled out all fields and displays a message to the user if they haven't
                if (etId.isEmpty() || etCustomer.isEmpty() || etOrder.isEmpty() || etQuantity.isEmpty() || etPrice.isEmpty() || etTotal.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill out all options", Toast.LENGTH_LONG).show();
                } else {
                    // calls the function updatePerson from the databaseHelper class and sends the details the user has filled out to that function
                    openHelper.updatePerson(etId, company, etCustomer, etOrder, etQuantity, etPrice, etTotal);
                    Toast.makeText(getApplicationContext(), "Statement Edited Successfully", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Function to assign what the user has entered, to strings in order to update the database
    public void getTextView() {
        etIdAdd2 = findViewById(R.id.etIdAdd2);
        etCustomerAdd2 = findViewById(R.id.etCustomerAdd2);
        etOrderAdd2 = findViewById(R.id.etOrderAdd2);
        etQuantityAdd2 = findViewById(R.id.etQuantityAdd2);
        etPriceAdd2 = findViewById(R.id.etPriceAdd2);
        etTotalAdd2 = findViewById(R.id.etTotalAdd2);
        btnEdit=(Button)findViewById(R.id.btnEdit);

        String etId = etIdAdd2.getText().toString();
        String etCustomer = etCustomerAdd2.getText().toString();
        String etOrder = etOrderAdd2.getText().toString();
        String etQuantity = etQuantityAdd2.getText().toString();
        String etPrice = etPriceAdd2.getText().toString();
        String etTotal = etTotalAdd2.getText().toString();
    }
}
