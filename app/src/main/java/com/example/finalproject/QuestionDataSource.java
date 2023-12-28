//package com.example.finalproject;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.RadioButton;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;public class Answer extends AppCompatActivity {
//    private SQLiteDatabase database;
//    private int totalScore = 0;
//    private int finalCorrectAnswerIndex = -1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_answer);
//
//        // Ánh xạ các thành phần giao diện ở đây
//
//        // Mở cơ sở dữ liệu
//        String dbName = "questions.db";
//        database = SQLiteDatabase.openDatabase(getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);
//
//        loadNextQuestion(); // Load câu hỏi đầu tiên
//
//        Button answerButton = findViewById(R.id.AnswerButton);
//        answerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int selectedAnswerIndex = getSelectedAnswerIndex(answerA, answerB, answerC, answerD);
//                if (selectedAnswerIndex == finalCorrectAnswerIndex) {
//                    totalScore++;
//                }
//                loadNextQuestion(); // Chuyển sang câu hỏi tiếp theo sau khi trả lời
//            }
//        });
//    }
//
//    private void loadNextQuestion() {
//        Cursor resultSet = database.rawQuery("SELECT * FROM questions ORDER BY RANDOM() LIMIT 1", null);
//
//        if (resultSet != null && resultSet.moveToFirst()) {
//            String question = getColumnValue(resultSet, "name");
//            String optionA = getColumnValue(resultSet, "AnswerA");
//            String optionB = getColumnValue(resultSet, "AnswerB");
//            String optionC = getColumnValue(resultSet, "AnswerC");
//            String optionD = getColumnValue(resultSet, "AnswerD");
//            int correctAnswerIndex = getColumnInt(resultSet, "rightCorrect");
//
//            // Hiển thị câu hỏi và các lựa chọn mới lên giao diện
//
//            // Cập nhật giá trị cuối cùng để so sánh trong các bước tiếp theo
//            finalCorrectAnswerIndex = correctAnswerIndex;
//        } else {
//            // Không còn câu hỏi mới
//            Intent intent = new Intent(Answer.this, DisplayResult.class);
//            intent.putExtra("score", totalScore);
//            startActivity(intent);
//            finish(); // Kết thúc activity Answer sau khi hiển thị kết quả
//        }
//
//        if (resultSet != null) {
//            resultSet.close();
//        }
//    }
//
//    // Các phương thức helper để lấy giá trị từ Cursor
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (database != null) {
//            database.close();
//        }
//    }
//}
