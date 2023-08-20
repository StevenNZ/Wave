package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wave.R;
import com.example.wave.ViewModel.ProfileViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private ProfileViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView usernameGreet = findViewById(R.id.profileUsername);
        Button signoutBtn = findViewById(R.id.signoutBtn);

        model = new ViewModelProvider(this).get(ProfileViewModel.class);

        FirebaseUser user = model.getAuthenticatedUser().getValue();

        if (user != null) {
            String greet = "Hi " + user.getDisplayName() + "!";
            usernameGreet.setText(greet);
        }

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.signOutUser();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);

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
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (itemID == R.id.bottom_profile){
                return true;
            } else {
                return false;
            }
        });
    }
}