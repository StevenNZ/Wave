package com.example.initial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Category> categoryList = DataProvider.getCategories();
        CategoryAdapter itemsAdapter = new CategoryAdapter (this, R.layout.category_list_item, categoryList);
        ListView recyclerView = findViewById(R.id.category_recycler_view);
        recyclerView.setAdapter(itemsAdapter);






    }
}