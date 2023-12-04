package com.example.finalproject;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class categoryAdapter extends BaseAdapter {

        //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
        final ArrayList<category> listCategory;

        categoryAdapter(ArrayList<category> listCategory) {
            this.listCategory = listCategory;
        }

        @Override
        public int getCount() {
            //Trả về tổng số phần tử, nó được gọi bởi ListView
            return listCategory.size();
        }

        @Override
        public Object getItem(int position) {
            //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
            //có chỉ số position trong listProduct
            return listCategory.get(position);
        }

        @Override
        public long getItemId(int position) {
            //Trả về một ID của phần
            return listCategory.get(position).categoryID;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
            //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
            //Nếu null cần tạo mới

            View viewProduct;
            if (convertView == null) {
                viewProduct = View.inflate(parent.getContext(), R.layout.viewcategory, null);
            } else viewProduct = convertView;

            //Bind sữ liệu phần tử vào View
            category Category = (category) getItem(position);

            ((TextView) viewProduct.findViewById(R.id.namecategory)).setText(String.format("%s", Category.name));
            ((TextView) viewProduct.findViewById(R.id.decreption)).setText(String.format("%s", Category.decreption));
            ((ImageView) viewProduct.findViewById(R.id.image)).setImageResource(((category) getItem(position)).image);

            return viewProduct;
        }


    }
