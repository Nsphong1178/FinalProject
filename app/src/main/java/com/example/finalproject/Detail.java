package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame6);

        // Retrieve the question text from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String questionText = intent.getStringExtra("QUESTION_TEXT");

            // Now you can use this question text to display in your detail activity
            TextView questionTextView = findViewById(R.id.question);
            questionTextView.setText(questionText);
        }
    }
}
