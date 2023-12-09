package com.example.finalproject;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class Answer extends AppCompatActivity {
    private SQLiteDatabase database;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame3);
        TextView content = findViewById(R.id.question);
        RadioButton answerA = findViewById(R.id.radioButtonA);
        RadioButton answerB = findViewById(R.id.radioButtonB);
        RadioButton answerC = findViewById(R.id.radioButtonC);
        RadioButton answerD = findViewById(R.id.radioButtonD);

        // Mở cơ sở dữ liệu
        String dbName = "questions.db"; // Thay thế bằng tên thực của file cơ sở dữ liệu
        database = SQLiteDatabase.openDatabase(getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);

        Cursor resultSet = database.rawQuery("SELECT * FROM questions", null);

        if (resultSet != null && resultSet.moveToFirst()) {
            int contentColumnIndex = resultSet.getColumnIndex("name");
            int answerAColumnIndex = resultSet.getColumnIndex("AnswerA");
            int answerBColumnIndex = resultSet.getColumnIndex("AnswerB");
            int answerCColumnIndex = resultSet.getColumnIndex("AnswerC");
            int answerDColumnIndex = resultSet.getColumnIndex("AnswerD");

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
