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
        int totalScore = getIntent().getIntExtra("score", 0);

        // Display the total score or perform other operations
        // For example, set a TextView to show the total score
        TextView scoreTextView = findViewById(R.id.score);
        scoreTextView.setText(String.valueOf(totalScore));
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayResult.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
