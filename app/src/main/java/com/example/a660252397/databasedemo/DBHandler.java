package com.example.a660252397.databasedemo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//TODO ANYTHING TO DO WITH THE DATABASE WILL BE FOUND IN THIS CLASS
public class DBHandler extends SQLiteOpenHelper{
    //define database variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    //define SQLite database variable
    private SQLiteDatabase database;
    //constructor
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //creates your tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " TEXT NOT NULL, " +
                COLUMN_LASTNAME + " TEXT NOT NULL" + ");";
        db.execSQL(query);
    }
    //makes updates to tables
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //delete the entire table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //recreate the table with the new properties
        onCreate(db);
    }

    //get reference to the database
    public DBHandler open() throws SQLException{
        database = getWritableDatabase();
        return this;
    }

    public void add(Contacts contact){
        //content values is built into android that
        // allows you to add several values in one statement
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, contact.getFirstname());
        values.put(COLUMN_LASTNAME, contact.getLastname());

        //call open method to get access to the database
        open();
        database.insert(TABLE_NAME, null, values);

        //close database when you are done
        close();
    }

    public void delete(String firstname, String lastname){
        //open database
        open();
        //delete the row with matching first and last name
        database.execSQL("DELETE FROM " +
                TABLE_NAME + " WHERE " +
                COLUMN_FIRSTNAME + "=\"" + firstname + "\"" + " AND " +
                COLUMN_LASTNAME + "=\"" + lastname + "\";");
        close();
    }

    public void deleteAll(){
        open();
        database.execSQL("DELETE FROM" + TABLE_NAME + ";");
        close();
    }

    public Cursor readEntry(){
        String[] allColumns = new String[]{
                COLUMN_ID, COLUMN_FIRSTNAME, COLUMN_LASTNAME
        };

        Cursor c = database.query(TABLE_NAME, allColumns,null,null,null,null,null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }

}
