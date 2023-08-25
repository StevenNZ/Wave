package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.wave.Adaptor.PopularAdaptor;
import com.example.wave.Domains.GetPopularProductsUseCase;
import com.example.wave.Domains.WishListOperationsUseCase;
import com.example.wave.R;
import com.example.wave.ViewModel.AuthenticationViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PopularAdaptor popularAdapter;

    private List<Popular> wishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recyclerView = findViewById(R.id.wishlit_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(WishlistActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        setBottomNavBar();

        fetchAndDisplayWishlist();


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
        WishListOperationsUseCase wishListOperationsUseCase = new WishListOperationsUseCase();

        wishListOperationsUseCase.getUserWishlist(authenticationViewModel.getUserID(), this::updateWishlist);
    }

    private void updateWishlist(List<Popular> resultList) {
        PopularAdaptor popularAdaptor = new PopularAdaptor(WishlistActivity.this, R.layout.popular_list_item, resultList, this::onItemClick);
        wishlist = resultList;
        recyclerView.setAdapter(popularAdaptor);
    }

    private void onItemClick(int position) {
        Popular currentItem = wishlist.get(position);
        openDiscographyDetail(currentItem);
    }

    private void openDiscographyDetail(Popular discography) {
        Intent intent = new Intent(WishlistActivity.this, DiscographyDetailActivity.class);
        intent.putExtra("DiscographyId", discography.getDiscographyId());
        intent.putExtra("ArtistName", discography.getAlbumArtist());
        startActivity(intent);
    }

}