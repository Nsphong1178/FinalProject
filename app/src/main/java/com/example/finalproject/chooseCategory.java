package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class chooseCategory  extends AppCompatActivity {
    ArrayList<category> listCategory;
    categoryAdapter CategoryListAdapter;
    ListView ListViewCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame2);

        Button viewListqs = findViewById(R.id.viewlistqs);
        viewListqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chooseCategory.this, ListQuestion.class);
                startActivity(intent);
            }
        });

        listCategory = new ArrayList<>();
        listCategory.add(new category(1, "Lịch sử", "Câu hỏi về lịch sử", R.drawable.lichsu));
        listCategory.add(new category(2, "Thể thao", "Câu hỏi về thể thao", R.drawable.thethao));
        listCategory.add(new category(3, "Địa lý", "Câu hỏi về lịch sử", R.drawable.dialy));
        listCategory.add(new category(4, "Khoa học", "Câu hỏi về thể thao", R.drawable.thethao));
        listCategory.add(new category(5, "Toán học", "Câu hỏi về lịch sử", R.drawable.toanhoc));
        listCategory.add(new category(6, "Nghệ thuật", "Câu hỏi về thể thao", R.drawable.nghethuat));
        listCategory.add(new category(7, "Văn học", "Câu hỏi về lịch sử", R.drawable.vanhoc));
        listCategory.add(new category(8, "Công nghệ", "Câu hỏi về thể thao", R.drawable.congnghe));

        CategoryListAdapter = new categoryAdapter(listCategory);

        ListViewCategory = findViewById(R.id.list_qs);
        ListViewCategory.setAdapter(CategoryListAdapter);

        ListViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category Category = (category) CategoryListAdapter.getItem(position);
                Intent intent = new Intent(chooseCategory.this, Answer.class);
                startActivity(intent);
            }
        });
    }
}