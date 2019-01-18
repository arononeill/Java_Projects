package com.example.aoneill98.organiser2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Contacts extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        final DatabaseHelper openHelper = new DatabaseHelper(this);
        db = openHelper.getWritableDatabase();
        String[] customers = {DatabaseHelper.CONTACTS_COL_1};
        int[] toView = {R.id.mobile_list};
        insertCustomer();
        Cursor cursor = db.rawQuery("SELECT * FROM customer ", null);//= openHelper.getListContents();
        //Cursor cursor = db.query(DatabaseHelper.CONTACTS_TABLE_NAME, customers,
          //      null, null, null, null, null);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Database Empty", Toast.LENGTH_SHORT).show();
        }
        
        else {
            //Toast.makeText(this, "sure look", Toast.LENGTH_SHORT).show();
            //while (cursor.moveToNext()) {
                Toast.makeText(this, "Database Items", Toast.LENGTH_SHORT).show();
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activty_listview, cursor, customers, toView);

                //ListView listView = (ListView) findViewById(R.id.mobile_list);
                //listView.setAdapter(adapter);
            //}
        }
    }

    public void insertCustomer () {
        ContentValues customer = new ContentValues();
        customer.put(DatabaseHelper.CONTACTS_COL_1, "Bob");
        customer.put(DatabaseHelper.CONTACTS_COL_1, "Pete");
        customer.put(DatabaseHelper.CONTACTS_COL_1, "PADDY");
        long id = db.insert(DatabaseHelper.CONTACTS_TABLE_NAME, null, customer);
    }
}
