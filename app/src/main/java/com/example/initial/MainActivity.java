package com.example.initial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryRecyclerInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Category> categoryList = CategoryDataProvider.getCategories();
        RecyclerView recyclerView = findViewById(R.id.category_recycler_view);
        // Set up the RecyclerView with a LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CategoryAdapter itemsAdapter = new CategoryAdapter(this, R.layout.category_list_item, categoryList, this);

        // Set the adapter for the RecyclerView
        recyclerView.setAdapter(itemsAdapter);
        int recyclerViewWidth = recyclerView.getWidth();

        layoutManager.scrollToPositionWithOffset(0,  recyclerViewWidth);

        List<Popular> popularList = PopularDataProvider.getPopular();
        PopularAdaptor popularAdapter = new PopularAdaptor (this, R.layout.popular_list_item, popularList);
        ListView listView = findViewById(R.id.popular_list_view);
        listView.setAdapter(popularAdapter);


    }

    @Override
    public void onItemClick(int position) {

        Intent resultIntent = new Intent(this, ResultActivity.class);

        //this is where you get the code to get the category they clicked on using the getters

        startActivity(resultIntent);

    }
}