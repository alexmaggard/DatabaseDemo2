package com.example.a660252397.databasedemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    //define the widget variable layout
    private TableLayout tableLayout;
    //define SQLite controller
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //get reference to the layout widget
        tableLayout = (TableLayout) findViewById(R.id.activity_test);
        //instantiate the handler contructor
        dbHandler = new DBHandler(this);
        //call the build table method
        BuildTable();
    }

    private void BuildTable(){
        dbHandler.open();
        Cursor c = dbHandler.readEntry();
        int rows = c.getCount();
        int cols = c.getColumnCount();
        c.moveToFirst();

        //outter for loop
        for(int i = 0; i< rows; i++){
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            //inner loop
            for(int j=0;j<cols;i++){
                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(18);
                tv.setPadding(0,5,0,5);
                tv.setText(c.getString(j));
                row.addView(tv);
            }
            c.moveToNext();
            tableLayout.addView(row);
        }
        dbHandler.close();
    }
}
