package com.example.aoneill98.organiser2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class Statements extends AppCompatActivity {

    Button btnAdd, btnEdit;
    ImageButton btnDelete, btnDelete2, btnDelete3;
    TextView etId2, etCustomer2, etOrder2, etQuantity2, etPrice2, etTotal2;
    TextView etId3, etCustomer3, etOrder3, etQuantity3, etPrice3, etTotal3;
    TextView etId4, etCustomer4, etOrder4, etQuantity4, etPrice4, etTotal4, tvCompanyHeaderStatements;
    public final static String DEFAULT="N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statements);

        tvCompanyHeaderStatements = findViewById(R.id.tvCompanyHeaderStatments);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnDelete=(ImageButton)findViewById(R.id.btnDelete);
        btnDelete2=(ImageButton)findViewById(R.id.btnDelete2);
        btnDelete3=(ImageButton)findViewById(R.id.btnDelete3);
        setText();

        // Checks if the add statement button was clicked and takes the user to the appropiate page/class/xml layout
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Statements.this, AddStatement.class);
                startActivity(intent);
            }
        });

        // Checks if the edit statement button was clicked and takes the user to the appropiate page/class/xml layout
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Statements.this, EditStatement.class);
                startActivity(intent);
            }
        });

    }

    public void setText() {
        // Used for accessing the session variables
        SharedPreferences sharedPreferences=getSharedPreferences("mydata", Context.MODE_PRIVATE);
        String company=sharedPreferences.getString("company", DEFAULT);
        final DatabaseHelper openHelper = new DatabaseHelper(this);

        // Returns a cursor from the databaseHelper function getAllData which contains everything from the statements table
        final Cursor c = openHelper.getAllData();

        // This uses individual variables to get which each textView object
        etId2=(TextView)findViewById(R.id.etId2);
        etCustomer2=(TextView)findViewById(R.id.etCustomer2);
        etOrder2=(TextView)findViewById(R.id.etOrder2);
        etQuantity2=(TextView) findViewById(R.id.etQuantity2);
        etPrice2=(TextView) findViewById(R.id.etPrice2);
        etTotal2=(TextView) findViewById(R.id.etTotal2);

        etId3=(TextView)findViewById(R.id.etId3);
        etCustomer3=(TextView) findViewById(R.id.etCustomer3);
        etOrder3=(TextView) findViewById(R.id.etOrder3);
        etQuantity3=(TextView) findViewById(R.id.etQuantity3);
        etPrice3=(TextView) findViewById(R.id.etPrice3);
        etTotal3=(TextView) findViewById(R.id.etTotal3);

        etId4=(TextView)findViewById(R.id.etId4);
        etCustomer4=(TextView) findViewById(R.id.etCustomer4);
        etOrder4=(TextView) findViewById(R.id.etOrder4);
        etQuantity4=(TextView) findViewById(R.id.etQuantity4);
        etPrice4=(TextView) findViewById(R.id.etPrice4);
        etTotal4=(TextView) findViewById(R.id.etTotal4);

        // gets the cursor c to access the values within each table column and assign them all to individual int variables
        final int id = c.getColumnIndex("ID");
        int company2 = c.getColumnIndex("Company");
        int customer = c.getColumnIndexOrThrow("Customer");
        int orders = c.getColumnIndex("Orders");
        int quantity = c.getColumnIndex("Quantity");
        int price = c.getColumnIndex("Price");
        int total = c.getColumnIndex("Total");

        // This uses the cursor to display the table customer's most recent tow of data to each individual textView
        c.moveToLast();
        etId2.setText(c.getString(id));
        etCustomer2.setText(c.getString(customer));
        etOrder2.setText(c.getString(orders));
        etQuantity2.setText(c.getString(quantity));
        etPrice2.setText(c.getString(price));
        etTotal2.setText(c.getString(total));

        // This uses the cursor to display the table customer's second most recent tow of data to each individual textView
        c.moveToPrevious();
        etId3.setText(c.getString(id));
        etCustomer3.setText(c.getString(customer));
        etOrder3.setText(c.getString(orders));
        etQuantity3.setText(c.getString(quantity));
        etPrice3.setText(c.getString(price));
        etTotal3.setText(c.getString(total));

        // This uses the cursor to display the table customer's third most recent tow of data to each individual textView
        c.moveToPrevious();
        etId4.setText(c.getString(id));
        etCustomer4.setText(c.getString(customer));
        etOrder4.setText(c.getString(orders));
        etQuantity4.setText(c.getString(quantity));
        etPrice4.setText(c.getString(price));
        etTotal4.setText(c.getString(total));

        // Displays the company's name and the word Statements in the header of this page
        String both = company + " Statements  ";
        tvCompanyHeaderStatements.setText(both);

        /* Buttons to check whether the user has opted to delete a row from the statements table and accesses the openHelper class
         function deleteStatement to do so */
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Successfully deleted the top record", Toast.LENGTH_LONG).show();
                c.moveToLast();
                openHelper.deleteStatement(c.getString(id));
            }
        });

        /* Buttons to check whether the user has opted to delete a row from the statements table and accesses the openHelper class
         function deleteStatement to do so */
        btnDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Successfully deleted the middle record", Toast.LENGTH_LONG).show();
                c.moveToLast();
                c.moveToPrevious();
                openHelper.deleteStatement(c.getString(id));
            }
        });

        /* Buttons to check whether the user has opted to delete a row from the statements table and accesses the openHelper class
         function deleteStatement to do so */
        btnDelete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Successfully deleted the bottom record", Toast.LENGTH_LONG).show();
                c.moveToLast();
                c.moveToPrevious();
                c.moveToPrevious();
                openHelper.deleteStatement(c.getString(id));
            }
        });

    }
}
