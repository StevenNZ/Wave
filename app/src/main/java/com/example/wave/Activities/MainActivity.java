package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wave.Activities.Category;
import com.example.wave.Activities.CategoryDataProvider;
import com.example.wave.Activities.CategoryRecyclerInterface;
import com.example.wave.Activities.Popular;
import com.example.wave.Activities.ResultActivity;
import com.example.wave.R;
import com.example.wave.Adaptor.CategoryAdapter;
import com.example.wave.Adaptor.PopularAdaptor;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryRecyclerInterface {

    private SearchView mainSearch;
    private TextView searchLabel;
    private int originalSearchWidth;

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

        List<Popular> popularList = Popular.PopularDataProvider.getPopular();
        PopularAdaptor popularAdapter = new PopularAdaptor (this, R.layout.popular_list_item, popularList);
        ListView listView = findViewById(R.id.popular_list_view);
        listView.setAdapter(popularAdapter);

        mainSearch = findViewById(R.id.main_search);
        searchLabel = findViewById(R.id.search_label);
        originalSearchWidth = mainSearch.getLayoutParams().width;

        mainSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent searchIntent = new Intent(MainActivity.this, ResultActivity.class);
                searchIntent.putExtra("query", query);
//                //this is where you get the code to get the category they clicked on using the getters
                startActivity(searchIntent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //
                return false;
            }
        });

        //remove search text and increase width
        mainSearch.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLabel.setVisibility(View.GONE);
                mainSearch.setMaxWidth(600);
            }
        });

        //on close get original view back
        mainSearch.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchLabel.setVisibility(View.VISIBLE);
                mainSearch.getLayoutParams().width = originalSearchWidth;
                mainSearch.requestLayout();
                return false;
            }
        });


    }

    @Override
    public void onItemClick(int position) {

        //how would I get the categoryID and pass it

        Intent resultIntent = new Intent(this, ResultActivity.class);

        //this is where you get the code to get the category they clicked on using the getters

        startActivity(resultIntent);

    }

}