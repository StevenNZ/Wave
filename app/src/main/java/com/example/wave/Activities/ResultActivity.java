package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wave.Dataproviders.DiscographyProvider;
import com.example.wave.Entities.Discography;
import com.example.wave.R;
import com.example.wave.Adaptor.PopularAdaptor;
import com.example.wave.ViewModel.MainViewModel;
import com.example.wave.ViewModel.ResultViewModel;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private SearchView searchView;
    private PopularAdaptor resultAdapater;

    private List<Popular> results;
    private ResultViewModel model;

    private MaterialToolbar topAppBar;

    //might have to put this into viewModel , ask Gurjot
    private void fetchAndDisplay(String query){
        SearchUseCase.generateDiscographyResults(query, new DiscographyResultsListener() {
            @Override
            public void onDiscographyResultsReady(List<Popular> resultList) {
                Log.d("SearchDebug", "total Result list = " + resultList);

                if(resultList.isEmpty()){
                    Toast.makeText(getBaseContext(), "Make sure fields are not empty", Toast.LENGTH_SHORT).show();
                }else{
                    results = resultList;
                    resultAdapater = new PopularAdaptor(ResultActivity.this, R.layout.popular_list_item, resultList);
                    ListView listView = findViewById(R.id.result_list_view);
                    topAppBar.setVisibility(View.VISIBLE);
                    listView.setAdapter(resultAdapater);
                }


            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        model = new ViewModelProvider(this).get(ResultViewModel.class);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);

        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
        topAppBar.setVisibility(View.GONE);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            boolean isLoggedIn = model.isLogin();
            int itemID = item.getItemId();

            if (itemID == R.id.bottom_home){
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (itemID == R.id.bottom_search){
                return true;
            } else if (!isLoggedIn) {
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            } else if (itemID == R.id.bottom_wishlist){
                startActivity(new Intent(this, WishlistActivity.class));
                return true;
            } else if (itemID == R.id.bottom_cart){
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (itemID == R.id.bottom_profile){
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            } else {
                return false;
            }
        });

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");



        fetchAndDisplay(query);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.results_menu, menu);

        MenuItem resultsMenu = menu.findItem(R.id.result_search_view);
        searchView = (SearchView) resultsMenu.getActionView();
        setupSearchViewListener();

        return true;
    }
    private void setupSearchViewListener() {
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SearchDebug", "IS THIS BEING CALLED = " + query);
                //when the user presses enter what happens.
                fetchAndDisplay(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //any changes (autocomplete)

                List<Popular> filteredList = new ArrayList<>();

                Log.d("SearchDebug", "onQueryTextChange: newText = " + newText);
                Log.d("SearchDebug", "onQueryTextChange: OnTextChange = " + results);


                for (Popular popular: results){
                    //if album name in newText or artist name in new text
                    if(popular.getAlbumName().toLowerCase().contains(newText.toLowerCase())
                    || popular.getAlbumArtist().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(popular);
                    }
                }
                if(!filteredList.isEmpty()){
                    //this makes sure that when the list is empty we don't do anything
                    // can refactor to make better but idc
                    resultAdapater.setFilteredList(filteredList);
                }
                return true;
            }
        });
        }
    }
    
}
