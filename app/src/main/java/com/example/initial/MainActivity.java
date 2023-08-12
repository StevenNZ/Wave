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
        RecyclerView recyclerView = findViewById(R.id.category_recycler_view);
        // Set up the RecyclerView with a LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CategoryAdapter itemsAdapter = new CategoryAdapter(this, R.layout.category_list_item, categoryList);

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(itemsAdapter);
        layoutManager.scrollToPositionWithOffset(1, recyclerView.getHeight() / 2);






    }
}