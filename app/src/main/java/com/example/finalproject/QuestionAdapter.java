package com.example.finalproject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionAdapter extends BaseAdapter{

    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final ArrayList<QuestionClass> listquestion;

    QuestionAdapter(ArrayList<QuestionClass> listquestion) {
        this.listquestion = listquestion;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listquestion.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listquestion.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listquestion.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.viewquestion, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
//        category Category = (category) getItem(position);
        QuestionClass qs = (QuestionClass)getItem(position);

        ((TextView) viewProduct.findViewById(R.id.namequestion)).setText(String.format("%s", qs.name));

        return viewProduct;
    }


}
