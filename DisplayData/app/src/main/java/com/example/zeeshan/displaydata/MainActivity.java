package com.example.zeeshan.displaydata;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Dbhelper db= new Dbhelper(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoCompleteTextView txtview= (AutoCompleteTextView)findViewById(R.id.auct1);
        db.deleteAllItems();
        db.insertData("Strix GTX1080");
        db.insertData("ASUS Gaming Motherboard");
        db.insertData("Samsung galaxy S8");
        db.insertData("VR Gear");
        db.insertData("iPhone 7");
        db.insertData("Panasonic Viera Smart TV UHD");
        String[] strings=db.SelectAllData();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,R.layout.text_view, strings);

        txtview.setAdapter(arrayAdapter);
        txtview.setThreshold(2);
    }
}