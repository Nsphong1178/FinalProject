package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> listProduct;
    ProductListViewAdapter productListViewAdapter;
    ListView listViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] itemNames = getResources().getStringArray(R.array.title);

        //Khoi tao ListProduct
        listProduct = new ArrayList<>();
        listProduct.add(new Product(1, "Hoan Kiem", R.drawable.hoankiem));
        listProduct.add(new Product(2, "Ho Chi Minh Mausoleum", R.drawable.hochiminh));
        listProduct.add(new Product(3, "West Lake", R.drawable.westlake));
        listProduct.add(new Product(4, "Hanoi’s Old Quarter", R.drawable.oldquater));
        listProduct.add(new Product(5, "Temple of Literature", R.drawable.literaturetemple));
        listProduct.add(new Product(6, "Vietnam Museum of Ethnology", R.drawable.ethnology));
        listProduct.add(new Product(7, "Hoa Lo prison", R.drawable.maison));
        listProduct.add(new Product(8, "Ho Chi Minh Presidential Palace", R.drawable.precidental));
        listProduct.add(new Product(9, "Thang Long Water Puppet Theater", R.drawable.waterpuppet));
        listProduct.add(new Product(10, "One Pillar Pagoda", R.drawable.onepillar));

        productListViewAdapter = new ProductListViewAdapter(listProduct);

        listViewProduct = findViewById(R.id.listproduct);
        listViewProduct.setAdapter(productListViewAdapter);


        //Lắng nghe bắt sự kiện một phần tử danh sách được chọn
        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = (Product) productListViewAdapter.getItem(position);
                //Làm gì đó khi chọn sản phẩm (ví dụ tạo một Activity hiện thị chi tiết, biên tập ..)
                Toast.makeText(MainActivity.this, product.name, Toast.LENGTH_LONG).show();
                String itemName = getResources().getStringArray(R.array.title)[position];
                String itemDescription = getResources().getStringArray(R.array.descriptions)[position];
                int imageResourceId = getResources().obtainTypedArray(R.array.images).getResourceId(position, -1);

                // Mở Activity chi tiết với thông tin tương ứng
                Intent intent = new Intent(MainActivity.this, Descreption.class);
                intent.putExtra("item_name", itemName);
                intent.putExtra("item_description", itemDescription);
                intent.putExtra("item_image", imageResourceId);
                startActivity(intent);
            }
        });

//        setContentView(R.layout.product_view);
//        ImageView Img = findViewById(R.id.image);
//        Img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(MainActivity.this, Descreption.class);
//                startActivity(intent);
//            }
//        });
    }
}