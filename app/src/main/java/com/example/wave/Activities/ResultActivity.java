package com.example.wave.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.wave.Adaptor.DiscographyAdapter;
import com.example.wave.Dataproviders.DiscographyProvider;
import com.example.wave.Domains.GetDiscographyUseCase;
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
    private DiscographyAdapter resultsAdapter;

    private BottomNavigationView bottomNavigationView;

    private RecyclerView recyclerView;

    private MenuItem resultsMenu;

    private List<Discography> results;
    private ResultViewModel model;

    private MaterialToolbar topAppBar;

    private String[] filterItems = {"$10 - $19", "$20 - $29", "$30 - $39", "$40 - $49", "$50 - $59", "$60 - $69"};
    private String[] sortItems = {"$ - $$$", "$$$ - $", "A - Z", "Z - A"};
    private String currentFilter;
    private String currentSort;

    private AutoCompleteTextView filterAutoCompleteText;
    private AutoCompleteTextView sortAutoCompleteText;
    private ArrayAdapter<String> adapterItems;






    private RecyclerView.LayoutManager getLayoutManager(String categoryId) {
        if ("kpop".equals(categoryId)) {
            return new GridLayoutManager(ResultActivity.this, 2);
        } else {
            return new LinearLayoutManager(ResultActivity.this, LinearLayoutManager.VERTICAL, false);
        }
    }

    private int getLayoutResource(String categoryId) {
        switch (categoryId) {
            case "kpop":
                return (R.layout.kpop_list_item);
            case "pop":
                return (R.layout.pop_list_item);
            case "hiphop":
                return (R.layout.hiphop_list_item);
            default:
                return (R.layout.popular_list_item);
        }
    }

    private void displayBasedQuery(String query) throws Exception {
        if (!query.isEmpty()) {
            int layoutResourceId = getLayoutResource("");
            RecyclerView.LayoutManager layoutManager = getLayoutManager("");
            recyclerView.setLayoutManager(layoutManager);
            model.getDiscographyListBySearch(query).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    List<Discography> displayDiscographyList = task.getResult();
                    results = displayDiscographyList;
                    resultsAdapter = new DiscographyAdapter(ResultActivity.this, layoutResourceId, displayDiscographyList, new PopularRecylcerInterface() {
                        @Override
                        public void onItemClick(int position) {

                            // Handle item click here if needed
                            Discography selectedDiscography = displayDiscographyList.get(position);
                            String discographyId = selectedDiscography.getDiscographyID();
                            String artistName = selectedDiscography.getArtistID();


                            Intent intent = new Intent(ResultActivity.this, DiscographyDetailActivity.class);
                            intent.putExtra("DiscographyId", discographyId);
                            intent.putExtra("ArtistName", artistName);
                            startActivity(intent);


                        }
                    });
                    resultsAdapter.setIsearchResults(true);
                    recyclerView.setAdapter(resultsAdapter);

                }
            });

        }
    }

    private void displayBasedCategory(String categoryID) throws Exception {

        int layoutResourceId = getLayoutResource(categoryID);
        RecyclerView.LayoutManager layoutManager = getLayoutManager(categoryID);

       model.getDiscographyListByCategory(categoryID).addOnCompleteListener(task -> {
           if (task.isSuccessful()) {
               List<Discography> displayDiscographyList = task.getResult();
               recyclerView.setLayoutManager(layoutManager);
               Log.d("SearchDebug", "displayBasedCategory: displayDiscographyList = " + displayDiscographyList);
               resultsAdapter = new DiscographyAdapter(ResultActivity.this, layoutResourceId, displayDiscographyList, new PopularRecylcerInterface() {
                   @Override
                   public void onItemClick(int position) {

                       // Handle item click here if needed
                       Discography selectedDiscography = displayDiscographyList.get(position);
                       String discographyId = selectedDiscography.getDiscographyID();
                       String artistName = selectedDiscography.getArtistID();


                       Intent intent = new Intent(ResultActivity.this, DiscographyDetailActivity.class);
                       intent.putExtra("DiscographyId", discographyId);
                       intent.putExtra("ArtistName", artistName);
                       startActivity(intent);

                   }
               });
               resultsAdapter.setIsearchResults(false);
               recyclerView.setAdapter(resultsAdapter);
           } else {
               Exception exception = task.getException();
               // Handle the exception
           }
       });
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        model = new ViewModelProvider(this).get(ResultViewModel.class);

        filterAutoCompleteText = findViewById(R.id.filterAutoCompleteText);
        sortAutoCompleteText = findViewById(R.id.sortAutoCompleteText);

        adapterItems = new ArrayAdapter<>(this, R.layout.sort_list_item, filterItems);
        filterAutoCompleteText.setAdapter(adapterItems);
        adapterItems = new ArrayAdapter<>(this, R.layout.sort_list_item, sortItems);
        sortAutoCompleteText.setAdapter(adapterItems);


        recyclerView = findViewById(R.id.result_list_view);
        recyclerView.setAdapter(resultsAdapter);


        filterAutoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentFilter = parent.getItemAtPosition(position).toString();




            }
        });

        sortAutoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentSort = parent.getItemAtPosition(position).toString();


            }
        });

        setBottomNavBar();
        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);



        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        String categoryId = intent.getStringExtra("categoryID");
        Log.d("SearchDebug", "CategoryID = " + categoryId);
        Log.d("SearchDebug", "query = " + query);

        //entered valid search from main
        if(query != null && categoryId == null){
            // only if not null
            try {
                displayBasedQuery(query);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (query == null && categoryId != null) {
            //clicked a valid category

            try {
                displayBasedCategory(categoryId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setBottomNavBar(){

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);
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



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.results_menu, menu);

        resultsMenu = menu.findItem(R.id.result_search_view);
        searchView = (SearchView) resultsMenu.getActionView();
        setupSearchViewListener();


        return true;
    }
    private void setupSearchViewListener() {
        setupSearchClickListener();
        //setupCloseListener();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SearchDebug", "IS THIS BEING CALLED = " + query);
                //when the user presses enter what happens.

                try {
                    displayBasedQuery(query);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                if (results == null || results.isEmpty()) {
                    return true; // Return early if the results list is not ready
                }

                List<Discography> filteredList = new ArrayList<>();

                Log.d("SearchDebug", "onQueryTextChange: newText = " + newText);
                Log.d("SearchDebug", "onQueryTextChange: OnTextChange = " + results);


                for (Discography discography: results){
                    //if album name in newText or artist name in new text
                    if(discography.getReleaseName().toLowerCase().contains(newText.toLowerCase())
                    || discography.getArtistID().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(discography);
                    }
                }
                if(!filteredList.isEmpty()){
                    //this makes sure that when the list is empty we don't do anything
                    // can refactor to make better but idc
                    Log.d("SearchDebug", "onQueryTextChange: OnTextChange = " + resultsAdapter);
                    results = filteredList;
                    Log.d("SearchDebug", "onQueryTextChange: OnTextChange = " + results);
                    resultsAdapter.setFilteredList(filteredList);
                }
                return true;
            }
        });
        }
    }

    private void setupSearchClickListener() {
        resultsMenu.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                bottomNavigationView.setVisibility(View.GONE);
                // Called when the action view (SearchView) is expanded (opened)
                return true; // Return true to allow expanding the view
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Called when the action view (SearchView) is collapsed (closed)
                bottomNavigationView.setVisibility(View.VISIBLE);
                return true; // Return true to allow collapsing the view
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getVisibility() == View.GONE) {
            resultsMenu.collapseActionView();
            bottomNavigationView.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }







}
