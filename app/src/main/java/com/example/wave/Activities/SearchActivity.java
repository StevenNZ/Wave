package com.example.wave.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.wave.Adaptor.PopularAdaptor;
import com.example.wave.R;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        searchView = findViewById(R.id.empty_search);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // get the query and show the results
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //show autocomplete of the previous shit
                return false;
            }
        });

//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
//
//                //this is where you get the code to get the category they clicked on using the getters
//                startActivity(searchIntent);
//
//            }
//        });







    }
}
