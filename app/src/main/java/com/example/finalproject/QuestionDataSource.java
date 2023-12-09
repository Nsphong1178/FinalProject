//package com.example.finalproject;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;
//
//import com.example.finalproject.ClassQuestion;
//import com.example.finalproject.SQLHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuestionDataSource {
//    private SQLiteDatabase database;
//    private SQLHelper dbHelper;
//    private String[] allColumns = {
//            SQLHelper.COLUMN_ID,
//            SQLHelper.COLUMN_NAME,
//            SQLHelper.COLUMN_RIGHT_CORRECT,
//            SQLHelper.COLUMN_ANSWER_A,
//            SQLHelper.COLUMN_ANSWER_B,
//            SQLHelper.COLUMN_ANSWER_C,
//            SQLHelper.COLUMN_ANSWER_D
//    };
//
//    public QuestionDataSource(Context context) {
//        dbHelper = new SQLHelper(context);
//        database = dbHelper.getWritableDatabase(); ;
//    }
//
//    public void open() throws SQLException {
//        database = dbHelper.getWritableDatabase();
//    }
//
//    public void close() {
//        dbHelper.close();
//    }
//
//    public Cursor rawQuery(String query, String[] args) {
//        // Thực hiện truy vấn SQL bằng phương thức rawQuery của SQLiteDatabase
//        return database.rawQuery(query, args);
//    }
//    public List<ClassQuestion> getAllQuestions() {
//        List<ClassQuestion> questions = new ArrayList<>();
//
//        Cursor cursor = database.query(SQLHelper.TABLE_QUESTIONS,
//                allColumns, null, null, null, null, null);
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            ClassQuestion question = cursorToQuestion(cursor);
//            questions.add(question);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return questions;
//    }
//
//    private ClassQuestion cursorToQuestion(Cursor cursor) {
//        ClassQuestion question = new ClassQuestion();
//        question.setId(cursor.getLong(0));
//        question.setName(cursor.getString(1));
//        question.setRightCorrect(cursor.getInt(2));
//        question.setAnswerA(cursor.getString(3));
//        question.setAnswerB(cursor.getString(4));
//        question.setAnswerC(cursor.getString(5));
//        question.setAnswerD(cursor.getString(6));
//        return question;
//    }
//}
