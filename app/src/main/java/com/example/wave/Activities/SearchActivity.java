package com.example.wave.Activities;

import android.app.Activity;
import android.os.Bundle;
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

        searchView = findViewById(R.id.main_search);
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







    }
}
