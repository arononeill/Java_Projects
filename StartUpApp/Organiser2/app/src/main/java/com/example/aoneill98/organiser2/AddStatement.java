package com.example.aoneill98.organiser2;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStatement extends AppCompatActivity {

    Button btnInsert;
    EditText etCustomer, etOrder, etQuantity, etPrice, etTotal;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    public final static String DEFAULT="N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_statement);
        openHelper = new DatabaseHelper(this);

        // Accessing Session Variables
        final SharedPreferences sharedPreferences=getSharedPreferences("mydata", Context.MODE_PRIVATE);
        final String company=sharedPreferences.getString("company", DEFAULT);

        etCustomer=(EditText)findViewById(R.id.etCustomerAdd);
        etOrder=(EditText)findViewById(R.id.etOrderAdd);
        etQuantity=(EditText)findViewById(R.id.etQuantityAdd);
        etPrice=(EditText)findViewById(R.id.etPriceAdd);
        etTotal=(EditText)findViewById(R.id.etTotalAdd);
        btnInsert = (Button)findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getReadableDatabase();
                String Customer = etCustomer.getText().toString();
                String Orders = etOrder.getText().toString();
                String Quantity = etQuantity.getText().toString();
                String Price = etPrice.getText().toString();
                String Total = etTotal.getText().toString();

                // Checks if the fields are empty and if so displays a message to tell the user to fill them out
                if (Customer.isEmpty() || Orders.isEmpty() || Quantity.isEmpty() || Price.isEmpty() || Total.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill out all options", Toast.LENGTH_LONG).show();
                } else {
                    // calls the function insert data and passes the parameters containg what the user has entered into each textView
                    insertdata(company, Customer, Orders, Price, Quantity, Total);
                    Toast.makeText(getApplicationContext(), "Statement Added Successfully", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    // Used to insert values into each column in the statement table
    public void insertdata(String company, String Customer, String Orders, String Price, String Quantity, String Total) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.STATEMENTS_COL_2, company);
        contentValues.put(DatabaseHelper.STATEMENTS_COL_3, Customer);
        contentValues.put(DatabaseHelper.STATEMENTS_COL_4, Orders);
        contentValues.put(DatabaseHelper.STATEMENTS_COL_5, Price);
        contentValues.put(DatabaseHelper.STATEMENTS_COL_6, Quantity);
        contentValues.put(DatabaseHelper.STATEMENTS_COL_7, Total);
        long id = db.insert(DatabaseHelper.STATEMENTS_TABLE_NAME, null, contentValues);
    }
}
