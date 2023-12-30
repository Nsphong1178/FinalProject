package com.example.finalproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detail extends AppCompatActivity {
    private DatabaseInitializer databaseInitializer; // Thêm biến DatabaseInitializer

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame6);

        // Retrieve the question ID from the intent
        Intent intent = getIntent();
        if (intent != null) {
            int questionId = intent.getIntExtra("QUESTION_ID", 0);
            SetQuestion(questionId);
        }

        databaseInitializer = new DatabaseInitializer(); // Khởi tạo DatabaseInitializer
        databaseInitializer.copyDatabaseFromAssets(this); // Gọi phương thức copyDatabaseFromAssets

        final TextView content = findViewById(R.id.question);
        final TextView answerA = findViewById(R.id.AnswerA);
        final TextView answerB = findViewById(R.id.AnswerB);
        final TextView answerC = findViewById(R.id.AnswerC);
        final TextView answerD = findViewById(R.id.AnswerD);
        final Button answerButton = findViewById(R.id.button);

    }

    private void SetQuestion(int questionId) {
        String dbName = "questions.db";
        database = SQLiteDatabase.openDatabase(getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);

        Cursor resultSet = database.rawQuery(
                "SELECT name, AnswerA, AnswerB, AnswerC, AnswerD, rightCorrect, level " +
                        "FROM questions WHERE _id = ?",
                new String[]{String.valueOf(questionId)}
        );

        if (resultSet != null && resultSet.moveToFirst()) {
            String question = getColumnValue(resultSet, "name");
            String optionA = getColumnValue(resultSet, "AnswerA");
            String optionB = getColumnValue(resultSet, "AnswerB");
            String optionC = getColumnValue(resultSet, "AnswerC");
            String optionD = getColumnValue(resultSet, "AnswerD");
            int Right = getColumnInt(resultSet, "rightCorrect");
            int level =  getColumnInt(resultSet, "level");

            TextView content = findViewById(R.id.question);
            TextView answerA = findViewById(R.id.AnswerA);
            TextView answerB = findViewById(R.id.AnswerB);
            TextView answerC = findViewById(R.id.AnswerC);
            TextView answerD = findViewById(R.id.AnswerD);
            TextView RightAnswer = findViewById(R.id.Right);
            Button Level = findViewById(R.id.button);


            content.setText(question);
            answerA.setText(optionA);
            answerB.setText(optionB);
            answerC.setText(optionC);
            answerD.setText(optionD);


            if(Right == 1){
                RightAnswer.setText("Đáp án đúng: " + optionA);
            } else if (Right == 2) {
                RightAnswer.setText("Đáp án đúng: " + optionB);
            }else if (Right == 3) {
                RightAnswer.setText("Đáp án đúng: " + optionC);
            }else if (Right == 4) {
                RightAnswer.setText("Đáp án đúng: " + optionD);
            }

            if(level == 1){
               Level.setText("Dễ");
            } else if (level == 2) {
                Level.setText("Khó");
            }

        }
    }

    private String getColumnValue(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return columnIndex != -1 ? cursor.getString(columnIndex) : "";
    }

    private int getColumnInt(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return columnIndex != -1 ? cursor.getInt(columnIndex) : -1;
    }



}
