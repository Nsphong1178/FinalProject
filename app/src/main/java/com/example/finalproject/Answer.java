package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Answer extends AppCompatActivity {
    private SQLiteDatabase database;
    private int totalScore = 0;
    private int finalCorrectAnswerIndex = -1;
    private DatabaseInitializer databaseInitializer;
    QuestionAdapter QuestionListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame3);
        databaseInitializer = new DatabaseInitializer();

        databaseInitializer.copyDatabaseFromAssets(this);

        Intent intent = getIntent();
        int levelID = intent.getIntExtra("type_qs", 0);
        int level = intent.getIntExtra("level_qs", 0);
        final TextView content = findViewById(R.id.question);
        final RadioButton answerA = findViewById(R.id.radioButtonA);
        final RadioButton answerB = findViewById(R.id.radioButtonB);
        final RadioButton answerC = findViewById(R.id.radioButtonC);
        final RadioButton answerD = findViewById(R.id.radioButtonD);
        final Button answerButton = findViewById(R.id.AnswerButton);

        SetQuestion();

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedAnswerIndex = getSelectedAnswerIndex(answerA, answerB, answerC, answerD);

                if (selectedAnswerIndex == finalCorrectAnswerIndex) {
                    if(level == 1){
                        totalScore++;
                    }
                    else{
                        totalScore = totalScore+ 2;
                    }
                    SetQuestion();
                } else {
                    Intent intent = new Intent(Answer.this, DisplayResult.class);
                    intent.putExtra("score", totalScore);
                    intent.putExtra("type_qs", levelID);
                    intent.putExtra("level_qs", level);
                    startActivity(intent);
                }
            }
        });
    }

    private void SetQuestion() {
        String dbName = "questions.db";
        database = SQLiteDatabase.openDatabase(getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);
        RadioGroup radioGroup = findViewById(R.id.RGroup); // Thay R.id.radioGroup bằng ID thực của RadioGroup trong layout của bạn

        Intent intent = getIntent();
        int levelID = intent.getIntExtra("type_qs", 0);
        int level = intent.getIntExtra("level_qs", 0);

        Cursor resultSet = database.rawQuery(
                "SELECT name, AnswerA, AnswerB, AnswerC, AnswerD, rightCorrect " +
                        "FROM questions WHERE level = ? AND levelID = ? ORDER BY RANDOM() LIMIT 1",
                new String[]{String.valueOf(level), String.valueOf(levelID)}
        );

        if (resultSet != null && resultSet.moveToFirst()) {
            String question = getColumnValue(resultSet, "name");
            String optionA = getColumnValue(resultSet, "AnswerA");
            String optionB = getColumnValue(resultSet, "AnswerB");
            String optionC = getColumnValue(resultSet, "AnswerC");
            String optionD = getColumnValue(resultSet, "AnswerD");
            finalCorrectAnswerIndex = getColumnInt(resultSet, "rightCorrect");

            TextView content = findViewById(R.id.question);
            RadioButton answerA = findViewById(R.id.radioButtonA);
            RadioButton answerB = findViewById(R.id.radioButtonB);
            RadioButton answerC = findViewById(R.id.radioButtonC);
            RadioButton answerD = findViewById(R.id.radioButtonD);

            content.setText(question);
            answerA.setText(optionA);
            answerB.setText(optionB);
            answerC.setText(optionC);
            answerD.setText(optionD);
        }

        if (resultSet != null) {
            resultSet.close();
        }
        radioGroup.clearCheck();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null) {
            database.close();
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

    private int getSelectedAnswerIndex(RadioButton... radioButtons) {
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isChecked()) {
                return i + 1;
            }
        }
        return -1;
    }
}
