package com.example.aoneill98.organiser2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    Cursor cursor;
    SQLiteDatabase db = this.getWritableDatabase();
    SQLiteOpenHelper openHelper;

    // initialises the database and tables with their columns within it
    private static final String DATABASE_NAME = "register.db";
    static final String TABLE_NAME = "register";
    public static final String COL_1="ID";
    static final String COL_2="Company";
    static final String COL_3="Owner";
    static final String COL_4="Password";
    public static final String COL_5="Email";
    public static final String COL_6="Location";
    public static final String COL_7="Phone";

    static final String STATEMENTS_TABLE_NAME = "statements";
    public static final String STATEMENTS_COL_1="ID";
    static final String STATEMENTS_COL_2="Company";
    static final String STATEMENTS_COL_3="Customer";
    public static final String STATEMENTS_COL_4="Orders";
    public static final String STATEMENTS_COL_5="Price";
    public static final String STATEMENTS_COL_6="Quantity";
    public static final String STATEMENTS_COL_7="Total";

    static final String CONTACTS_TABLE_NAME = "customer";
    public static final String CONTACTS_COL_1="customerName";

    private final Context context;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    // Creates the table which will store the user's details
    private static final String CREATE_TABLE = "CREATE TABLE " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Company TEXT, " +
            "Owner TEXT, " +
            "Password TEXT, " +
            "Email TEXT, " +
            "Location TEXT, " +
            "Phone TEXT)";

    // Creates the table which will store statements
    private static final String CREATE_STATEMENTS_TABLE = "CREATE TABLE " +STATEMENTS_TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Company TEXT, " +
            "Customer TEXT, " +
            "Orders TEXT, " +
            "Quantity TEXT, " +
            "Price TEXT, " +
            "Total TEXT)";

    // Creates the table which will store contacts
    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " +CONTACTS_TABLE_NAME+ "(customerName TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_STATEMENTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
        insertCustomer();
    }

    // Function used to delete a chosen statement
    public boolean deleteStatement(String rowId) {
        return db.delete(STATEMENTS_TABLE_NAME, STATEMENTS_COL_1 + "=" + rowId, null) > 0;
    }

    // Function used to edit/update a chosen statement
    public Boolean updatePerson(String Id, String company, String Customer, String Orders, String Price, String Quantity, String Total)
    {
        ContentValues args = new ContentValues();
        args.put(STATEMENTS_COL_2, company);
        args.put(STATEMENTS_COL_3, Customer);
        args.put(STATEMENTS_COL_4, Orders);
        args.put(STATEMENTS_COL_5, Price);
        args.put(STATEMENTS_COL_6, Quantity);
        args.put(STATEMENTS_COL_7, Total);
        return db.update(STATEMENTS_TABLE_NAME, args, STATEMENTS_COL_1 + "=" + Id, null) > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +STATEMENTS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    // Adds sample data to the contacts table
    public void insertCustomer () {
        ContentValues customer = new ContentValues();
        customer.put(DatabaseHelper.CONTACTS_COL_1, "Bob");
        customer.put(DatabaseHelper.CONTACTS_COL_1, "Pete");
    }

    public Cursor getListContents() {
        insertCustomer();
        Cursor data = db.rawQuery("SELECT * FROM customer " ,null);
        return data;
    }

    // Function to return a cursor with all the data from the statements tbale
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM statements ", null);
        return c;
    }
}
