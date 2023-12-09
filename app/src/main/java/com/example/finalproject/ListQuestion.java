package com.example.finalproject;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ListQuestion extends AppCompatActivity{
    private SQLiteDatabase database;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame5);
//        ListView listView = findViewById(R.id.listView_qs);
////        database = new SQLiteDatabase.(this);
//
//
////        List<ClassQuestion> values = database.getAllQuestions();
//
//        // use the SimpleCursorAdapter to show the
//        // elements in a ListView
//        ArrayAdapter<ClassQuestion> adapter = new ArrayAdapter<ClassQuestion>(this,
//                android.R.layout.simple_list_item_1, values);
//        listView.setAdapter(adapter);

    }
}