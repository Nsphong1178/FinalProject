package com.example.finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListQuestion extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> suggestionAdapter;
    private ArrayList<String> suggestionList;

    private List<String> displayedQuestionList; // Danh sách câu hỏi hiển thị

    private DatabaseInitializer databaseInitializer; // Thêm biến DatabaseInitializer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame5);
        databaseInitializer = new DatabaseInitializer(); // Khởi tạo DatabaseInitializer

        databaseInitializer.copyDatabaseFromAssets(this); // Gọi phương thức copyDatabaseFromAssets

        AllQuestion();
        SearchView searchView = findViewById(R.id.searchView);

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ListView suggestionListView = popupView.findViewById(R.id.suggestionListView);

        suggestionList = new ArrayList<>();
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(getDatabasePath("questions.db").getPath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = db.rawQuery("SELECT DISTINCT type FROM questions", null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String idType = cursor.getString(0);
                    suggestionList.add(idType);
                } while (cursor.moveToNext());
                cursor.close();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        suggestionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, suggestionList);
        suggestionListView.setAdapter(suggestionAdapter);

        searchView.setOnSearchClickListener(v -> showSuggestionPopup(popupWindow, searchView));
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                dismissSuggestionPopup(popupWindow);
            }
        });

        suggestionListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = suggestionList.get(position);
            searchView.setQuery(selectedItem, true);
            dismissSuggestionPopup(popupWindow);
            filterQuestionListByType(selectedItem);
        });

        ListView listView = findViewById(R.id.listView_qs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            String selectedQuestion = displayedQuestionList.get(position);

            int questionId = getQuestionIdByName(selectedQuestion);

            if (questionId != -1) {
                // Chuyển sang trang chi tiết câu hỏi kèm theo ID
                Intent intent = new Intent(ListQuestion.this, Detail.class);
                intent.putExtra("QUESTION_ID", questionId);
                startActivity(intent);
            } else {
                // Xử lý khi không tìm thấy ID của câu hỏi
                Toast.makeText(ListQuestion.this, "no", Toast.LENGTH_SHORT).show();
             // Log yourString với tag "YourTag"
            }
        });


     }

    private int getQuestionIdByName(String questionName) {
        int questionId = -1;

        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(getDatabasePath("questions.db").getPath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = db.rawQuery("SELECT * FROM questions WHERE name = ?", new String[]{questionName});

            if (cursor != null && cursor.moveToFirst()) {
                questionId = cursor.getInt(0);
                cursor.close();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionId;
    }

    private void filterQuestionListByType(String selectedIdType) {
        displayedQuestionList = getQuestionsByType(selectedIdType);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayedQuestionList);
        ListView listView = findViewById(R.id.listView_qs);
        listView.setAdapter(adapter);
    }

    private void AllQuestion() {
        displayedQuestionList = getAllQuestionsFromDatabase();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayedQuestionList);
    }

    private List<String> getAllQuestionsFromDatabase() {
        List<String> questionList = new ArrayList<>();
        String dbName = "questions.db";

        try {
            SQLiteDatabase database = SQLiteDatabase.openDatabase(getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = database.rawQuery("SELECT name FROM questions", null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String question = cursor.getString(0);
                    questionList.add(question);
                } while (cursor.moveToNext());

                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }

    private List<String> getQuestionsByType(String selectedIdType) {
        List<String> filteredQuestionList = new ArrayList<>();

        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(getDatabasePath("questions.db").getPath(), null, SQLiteDatabase.OPEN_READONLY);
            Cursor cursor = db.rawQuery("SELECT name FROM questions WHERE type = ?", new String[]{selectedIdType});

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String question = cursor.getString(0);
                    filteredQuestionList.add(question);
                } while (cursor.moveToNext());
                cursor.close();
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filteredQuestionList;
    }

    private void showSuggestionPopup(PopupWindow popupWindow, View anchorView) {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(anchorView);
        }
    }

    private void dismissSuggestionPopup(PopupWindow popupWindow) {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}
