package com.example.finalproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayResult extends AppCompatActivity {
    private DatabaseInitializer databaseInitializer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame4);
        Button buttonFinish = findViewById(R.id.buttonFinish);
        Button buttonRetry = findViewById(R.id.buttonRetry);
        int totalScore = getIntent().getIntExtra("score", 0);
        int levelID = getIntent().getIntExtra("type_qs", 0);
        int level = getIntent().getIntExtra("level_qs", 0);
        TextView scoreTextView = findViewById(R.id.score);
        scoreTextView.setText(String.valueOf(totalScore));


        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayResult.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayResult.this, Answer.class);
                intent.putExtra("type_qs",levelID );  // Truyền một String
                intent.putExtra("level_qs",level);
                startActivity(intent);
            }
        });

        try {
            databaseInitializer = new DatabaseInitializer();
            databaseInitializer.copyDatabaseFromAssetsScore(this);

            SQLiteDatabase data = SQLiteDatabase.openDatabase(getDatabasePath("score.db").getPath(), null, SQLiteDatabase.OPEN_READWRITE);

            Cursor cursor = data.rawQuery("SELECT * FROM score", null);
            int oldScore = 0;

            if (cursor != null && cursor.moveToFirst()) {
                oldScore = cursor.getInt(0);
                cursor.close();
            }

            ContentValues values = new ContentValues();
            values.put("score", oldScore + totalScore);

            data.update("score", values, null, null);

            data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
