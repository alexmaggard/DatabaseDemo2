package com.example.a660252397.databasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //define widget variables
    private EditText firstnameET, lastnameET;
    private Button addButton, deleteButton, showButton, showAllButton, deleteAllButton;

    //define database handler
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiate our database
        dbHandler=new DBHandler(this);
        //get reference to the widgets
        firstnameET = (EditText) findViewById(R.id.firstNameET);
        lastnameET = (EditText) findViewById(R.id.lastNameET);
        addButton= (Button) findViewById(R.id.addButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteAllButton = (Button) findViewById(R.id.deleteAllButton);
        showAllButton = (Button) findViewById(R.id.showAllButton);
        showButton = (Button) findViewById(R.id.showButton);
    }
    /********************************************************************
            CLICK METHODS
     ********************************************************************/
    public void showAllClick(View view) {
        Intent i = new Intent(this, DisplayActivity.class);
        startActivity(i);
    }
    public void showClick(View view) {}
    public void deleteAllClick(View view) {
        dbHandler.deleteAll();
        firstnameET.requestFocus();
    }
    public void deleteClick(View view) {
        String firstname = firstnameET.getText().toString();
        String lastname = lastnameET.getText().toString();
        dbHandler.delete(firstname, lastname);
        firstnameET.requestFocus();
    }
    public void addClick(View view) {
        Contacts contacts = new Contacts(
                firstnameET.getText().toString(),
                lastnameET.getText().toString());
        dbHandler.add(contacts);
        firstnameET.setText("");
        lastnameET.setText("");
        firstnameET.requestFocus();
    }
}
