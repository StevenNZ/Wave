package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wave.Adaptor.PopularAdaptor;
import com.example.wave.Domains.GetCategoriesUseCase;
import com.example.wave.Domains.GetPopularProductsUseCase;
import com.example.wave.R;
import com.example.wave.Adaptor.CategoryAdapter;
import com.example.wave.ViewModel.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private SearchView mainSearch;
    private int originalSearchWidth;
    private MainViewModel model;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        fetchAndDisplayPopular();
        fetchAndDisplayCategories();


//        List<Popular> popularList = Popular.PopularDataProvider.getPopular();
//        PopularAdaptor popularAdapter = new PopularAdaptor (this, R.layout.popular_list_item, popularList);
//        ListView listView = findViewById(R.id.popular_list_view);
//        listView.setAdapter(popularAdapter);

        mainSearch = findViewById(R.id.main_search);
        originalSearchWidth = mainSearch.getLayoutParams().width;
        
//        setupSearchViewListeners();

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
                return false;
            }
        });
        LinearLayout categoryLayout = findViewById(R.id.categoryLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) categoryLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            boolean isLoggedIn = model.isLogin();
            int itemID = item.getItemId();

            if (itemID == R.id.bottom_home){
                return true;
            } else if (itemID == R.id.bottom_search){
                startActivity(new Intent(this, ResultActivity.class));
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

    /**
     * shows popular discographies, updates dynamically based on user views
     */
    private void fetchAndDisplayPopular(){

        GetPopularProductsUseCase getPopularProductsUseCase = new GetPopularProductsUseCase();

        getPopularProductsUseCase.getPopularDiscography(new DiscographyResultsListener() {
            @Override
            public void onDiscographyResultsReady(List<Popular> resultList) {

                RecyclerView recyclerView = findViewById(R.id.popular_recycler_view);
                // Set up the RecyclerView with a LinearLayoutManager
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);

                PopularAdaptor popularAdaptor = new PopularAdaptor(MainActivity.this, R.layout.kpop_list_item, resultList, new PopularRecylcerInterface() {
                    @Override
                    public void onItemClick(int position) {
                        Popular currentDiscography = resultList.get(position);
                        Log.d("SearchDebug", "currentDiscog = " + currentDiscography);

                        //pass intent to Details activity
                        Intent intent = new Intent(MainActivity.this, DiscographyDetailActivity.class);
                        intent.putExtra("DiscographyId", currentDiscography.getDiscographyId());
                        intent.putExtra("ArtistName", currentDiscography.getAlbumArtist());
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(popularAdaptor);
            }
        });



    }
    private void fetchAndDisplayCategories(){

        GetCategoriesUseCase getCategoriesUseCase = new GetCategoriesUseCase();

        getCategoriesUseCase.getCategoryDetails(new CategoryResultsListener() {
            @Override
            public void onCategoryResultsReady(List<CategoryBreakdown> resultList) {
                Log.d("SearchDebug", "total Result list = " + resultList);

                if(resultList.isEmpty()){
                    //display something empty
                    Toast.makeText(getBaseContext(), "NO results found", Toast.LENGTH_SHORT).show();
                }else{
                    RecyclerView recyclerView = findViewById(R.id.category_recycler_view);
                    // Set up the RecyclerView with a LinearLayoutManager
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);

                    CategoryAdapter itemsAdapter = new CategoryAdapter(MainActivity.this, R.layout.category_list_item, resultList, new CategoryRecyclerInterface() {
                        @Override
                        public void onItemClick(int position) {

                            CategoryBreakdown clickedCategory = resultList.get(position);

                            // Do something with the clickedCategory, e.g., start a new activity
                            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                            intent.putExtra("categoryID", clickedCategory.getCategoryID());
                            startActivity(intent);

                        }
                    });

                    // Set the adapter for the RecyclerView
                    recyclerView.setAdapter(itemsAdapter);
                }
            }
        });
    }


//    private void setupSearchViewListeners() {
//        setupSearchClickListener();
//        setupCloseListener();
//    }
//
//    private void setupCloseListener() {
//        mainSearch.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                mainSearch.getLayoutParams().width = originalSearchWidth;
//                mainSearch.requestLayout();
//                return false;
//            }
//        });
//    }

//    private void setupSearchClickListener() {
//        //remove search text and increase width
//        mainSearch.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mainSearch.setMaxWidth(600);
//            }
//        });
//
//
//
//    }

//    @Override
//    public void onItemClick(int position) {
//
//        Log.d("SearchDebug", "POSITION = " + position);
//
//        //how would I get the categoryID and pass it
//
//        Intent resultIntent = new Intent(this, ResultActivity.class);
//
//        //this is where you get the code to get the category they clicked on using the getters
//
//        startActivity(resultIntent);
//
//    }

}