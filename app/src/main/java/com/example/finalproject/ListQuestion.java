package com.example.finalproject;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ListQuestion extends AppCompatActivity {
    private SQLiteDatabase database;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> idTypeAdapter; // Adapter cho danh sách id_type

    private SearchView searchView;
    private PopupWindow popupWindow;
    private ListView suggestionListView;
    private ArrayAdapter<String> suggestionAdapter;
    private ArrayList<String> suggestionList;

    private List<String> displayedQuestionList; // Danh sách câu hỏi hiển thị

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame5);

        ListView listView = findViewById(R.id.listView_qs);
//        List<String> allQuestions = getAllQuestionsFromDatabase();
//        List<String> allQuestions = getQuestionsByType("Lịch sử");
        AllQuestion();
        searchView = findViewById(R.id.searchView);

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        suggestionListView = popupView.findViewById(R.id.suggestionListView);

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

        searchView.setOnSearchClickListener(v -> showSuggestionPopup());
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                dismissSuggestionPopup();
            }
        });

        suggestionListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = suggestionList.get(position);
            searchView.setQuery(selectedItem, true);
            dismissSuggestionPopup();
            filterQuestionListByType(selectedItem);

//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                category Category = (category) suggestionAdapter.getItem(position);
//                Intent intent = new Intent(chooseCategory.this, Answer.class);
//                startActivity(intent);
//            }
        });

//        ListViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                category Category = (category) CategoryListAdapter.getItem(position);
//                Intent intent = new Intent(chooseCategory.this, Answer.class);
//                startActivity(intent);
//            }
//        });
    }
    private void filterQuestionListByType(String selectedIdType) {
        displayedQuestionList = getQuestionsByType(selectedIdType);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayedQuestionList);
        ListView listView = findViewById(R.id.listView_qs);
        listView.setAdapter(adapter);
    }

    private void AllQuestion() {
        displayedQuestionList = getAllQuestionsFromDatabase();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayedQuestionList);
        ListView listView = findViewById(R.id.listView_qs);
        listView.setAdapter(adapter);
    }



    private List<String> getAllQuestionsFromDatabase() {
        List<String> questionList = new ArrayList<>();
        String dbName = "questions.db";

        try {
            database = SQLiteDatabase.openDatabase(getDatabasePath(dbName).getPath(), null, SQLiteDatabase.OPEN_READONLY);
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
    private void showSuggestionPopup() {
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(searchView);
        }
    }

    private void dismissSuggestionPopup() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }
}
