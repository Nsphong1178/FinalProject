package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayResult extends AppCompatActivity {
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

    }

}
