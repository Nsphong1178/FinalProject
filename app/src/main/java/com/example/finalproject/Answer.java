package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

public class Answer extends AppCompatActivity {
    private SQLiteDatabase database;
    private int totalScore = 0; // Track total score

    private int level = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame3);

        TextView content = findViewById(R.id.question);
        RadioButton answerA = findViewById(R.id.radioButtonA);
        RadioButton answerB = findViewById(R.id.radioButtonB);
        RadioButton answerC = findViewById(R.id.radioButtonC);
        RadioButton answerD = findViewById(R.id.radioButtonD);
        Button answerButton = findViewById(R.id.AnswerButton);

        // Open the database
        String dbName = "questions.db";
        database = SQLiteDatabase.openDatabase(getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);

        Cursor resultSet = database.rawQuery("SELECT * FROM questions ORDER BY RANDOM() LIMIT 1", null);

        if (resultSet != null && resultSet.moveToFirst()) {
            // Fetching question and answers
            String question = getColumnValue(resultSet, "name");
            String optionA = getColumnValue(resultSet, "AnswerA");
            String optionB = getColumnValue(resultSet, "AnswerB");
            String optionC = getColumnValue(resultSet, "AnswerC");
            String optionD = getColumnValue(resultSet, "AnswerD");
            int correctAnswerIndex = getColumnInt(resultSet, "rightCorrect");

            if (!question.isEmpty()) {
                // Display question and answers
                content.setText(question);
                answerA.setText(optionA);
                answerB.setText(optionB);
                answerC.setText(optionC);
                answerD.setText(optionD);

                int finalCorrectAnswerIndex = correctAnswerIndex; // Store it as final for usage in listeners

                answerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check which answer was selected
                        int selectedAnswerIndex = getSelectedAnswerIndex(answerA, answerB, answerC, answerD);

                        if (selectedAnswerIndex == finalCorrectAnswerIndex) {
                            // Correct answer selected
                            totalScore++; // Increment score for correct answer
                            Intent intent = new Intent(Answer.this, Answer.class);
                            startActivity(intent);
                        } else {
                            // Incorrect answer selected
                            Intent intent = new Intent(Answer.this, DisplayResult.class);
                            intent.putExtra("score", totalScore); // Send total score to DisplayResult activity
                            startActivity(intent);
                        }
                    }
                });
            }
        }

        if (resultSet != null) {
            resultSet.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null) {
            database.close();
        }
    }

    // Helper method to safely get String column value
    private String getColumnValue(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return columnIndex != -1 ? cursor.getString(columnIndex) : "";
    }

    // Helper method to safely get int column value
    private int getColumnInt(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return columnIndex != -1 ? cursor.getInt(columnIndex) : -1;
    }

    // Helper method to get the index of the selected answer
    private int getSelectedAnswerIndex(RadioButton... radioButtons) {
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isChecked()) {
                return i + 1; // RadioButton indexes start from 1 in your database
            }
        }
        return -1; // No answer selected
    }
}
