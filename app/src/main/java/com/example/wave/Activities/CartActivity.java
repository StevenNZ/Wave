package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.wave.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_cart);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemID = item.getItemId();

            if (itemID == R.id.bottom_home){
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (itemID == R.id.bottom_search){
                startActivity(new Intent(this, ResultActivity.class));
                return true;
            } else if (itemID == R.id.bottom_wishlist){
                startActivity(new Intent(this, WishlistActivity.class));
                return true;
            } else if (itemID == R.id.bottom_cart){
                return true;
            } else if (itemID == R.id.bottom_profile){
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }
}