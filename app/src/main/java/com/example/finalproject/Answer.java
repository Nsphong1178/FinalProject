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

        // Mở cơ sở dữ liệu
        String dbName = "questions.db"; // Tên thực của file cơ sở dữ liệu
        database = SQLiteDatabase.openDatabase(getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);

        Cursor resultSet = database.rawQuery("SELECT * FROM questions", null);
        int contentColumnIndex = resultSet.getColumnIndex("name");
        int answerAColumnIndex = resultSet.getColumnIndex("AnswerA");
        int answerBColumnIndex = resultSet.getColumnIndex("AnswerB");
        int answerCColumnIndex = resultSet.getColumnIndex("AnswerC");
        int answerDColumnIndex = resultSet.getColumnIndex("AnswerD");
        int answerRightColumnIndex = resultSet.getColumnIndex("rightCorrect");

        if (resultSet != null && resultSet.moveToFirst()) {


            // Kiểm tra và hiển thị dữ liệu lên giao diện
            if (contentColumnIndex != -1) {
                content.setText(resultSet.getString(contentColumnIndex));
            }
            if (answerAColumnIndex != -1) {
                answerA.setText(resultSet.getString(answerAColumnIndex));
            }
            if (answerBColumnIndex != -1) {
                answerB.setText(resultSet.getString(answerBColumnIndex));
            }
            if (answerCColumnIndex != -1) {
                answerC.setText(resultSet.getString(answerCColumnIndex));
            }
            if (answerDColumnIndex != -1) {
                answerD.setText(resultSet.getString(answerDColumnIndex));
            }
        }
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi RadioButton được nhấn
                if (((RadioButton) v).isChecked()) {
                    answerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            if(resultSet.getString(answerRightColumnIndex).equals("1")){
                                Intent intent = new Intent(Answer.this, DisplayResult.class);
                                startActivity(intent);
//                            }
                        }
                    });
                }
            }
        });

        if (resultSet != null) {
            resultSet.close(); // Đóng Cursor sau khi sử dụng
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Đóng cơ sở dữ liệu khi Activity bị hủy
        if (database != null) {
            database.close();
        }
    }
}
