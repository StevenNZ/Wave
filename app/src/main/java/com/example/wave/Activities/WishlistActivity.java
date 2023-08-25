package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.wave.Adaptor.PopularAdaptor;
import com.example.wave.Adaptor.WishlistAdapter;
import com.example.wave.Domains.GetWishlistUseCase;
import com.example.wave.Entities.Order;
import com.example.wave.R;
import com.example.wave.ViewModel.AuthenticationViewModel;
import com.example.wave.ViewModel.WishlistViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PopularAdaptor popularAdapter;
    private RelativeLayout relativeLayout;
    private List<Order> wishlist;

    private WishlistViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recyclerView = findViewById(R.id.wishlist_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(WishlistActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        setBottomNavBar();

        model = new ViewModelProvider(this).get(WishlistViewModel.class);
        fetchAndDisplayWishlist();
        model.getWishlistLiveData().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> updatedWishlist) {
                // Update your UI with the new data
                // This code will be executed whenever the LiveData changes
                fetchAndDisplayWishlist();
            }
        });
    }

    private void setBottomNavBar() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_wishlist);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemID = item.getItemId();

            if (itemID == R.id.bottom_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (itemID == R.id.bottom_search) {
                startActivity(new Intent(this, ResultActivity.class));
                return true;
            } else if (itemID == R.id.bottom_wishlist) {
                return true;
            } else if (itemID == R.id.bottom_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (itemID == R.id.bottom_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }

    private void fetchAndDisplayWishlist() {
        AuthenticationViewModel authenticationViewModel = new AuthenticationViewModel();
        GetWishlistUseCase getWishlistUseCase = new GetWishlistUseCase();
        getWishlistUseCase.getWishlist(authenticationViewModel.getUserID()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Order> orders = task.getResult();
                showWishList(orders);

            } else {
                Exception exception = task.getException();
                // Handle the exception
            }
        });
    }

    private void showWishList(List<Order> resultList) {
        WishlistAdapter wishlistAdapter = new WishlistAdapter(WishlistActivity.this, R.layout.wishlist_list_item, resultList, this::onItemClick, model);
        relativeLayout = findViewById(R.id.cart_details);
        wishlist = resultList;
        Log.d("SearchDebug", "WISHLIST SHOULD BE HERE = " + resultList);

        if(wishlist.isEmpty()){
            relativeLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }else{
            relativeLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(wishlistAdapter);
        }

    }

    private void onItemClick(int position) {
        Order currentItem = wishlist.get(position);
        openDiscographyDetail(currentItem);
    }

    private void openDiscographyDetail(Order order) {
        Intent intent = new Intent(WishlistActivity.this, DiscographyDetailActivity.class);
        intent.putExtra("DiscographyId", order.getDiscographyID());
        intent.putExtra("ArtistName", order.getOrderID());
        startActivity(intent);
    }

}