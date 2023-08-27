package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wave.Adaptor.OrderHistoryAdapter;
import com.example.wave.Adaptor.PopularAdaptor;
import com.example.wave.Entities.CartOrder;
import com.example.wave.R;
import com.example.wave.ViewModel.ProfileViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

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

        fetchAndDisplayUserHistory(user.getUid());

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

//        List<List<CartOrder>> orderHistory = model.getOrderHistory(user.getUid());
//
//        Log.d("SEARCH DEBU", "IS THIS WORKING GEEEE + " + orderHistory.toString());
//
//        RecyclerView recyclerView = findViewById(R.id.order_history);
//        // Set up the RecyclerView with a LinearLayoutManager
//        LinearLayoutManager layoutManager = new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//
//        OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(ProfileActivity.this, R.layout.kpop_list_item, orderHistory, new PopularRecylcerInterface() {
//            @Override
//            public void onItemClick(int position) {
//            }
//        });
//        recyclerView.setAdapter(orderHistoryAdapter);
    }

    private void fetchAndDisplayUserHistory(String userID) {
        model.getOrderHistory(userID).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //List<List<CartOrder>> orderHistory = task.getResult();

                List<List<CartOrder>> orderHistory = new ArrayList<>();

                // Create some CartOrder objects for the first order
                List<CartOrder> order1 = new ArrayList<>();
                order1.add(new CartOrder("getrichordietryin", "type1", "user1", "getrichordietryin", "Get Rich or Die Tryin", "Artist 1", "https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fgetrichordietryin.png?alt=media&token=9164229a-e780-489c-9b94-1e5873714a96", "cassette", "1", "$10"));
                //order1.add(new CartOrder("getrichordietryin", "type1", "user1", "getrichordietryin", "Get Rich or Die Tryin", "Artist 1", "https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fgraduation.png?alt=media&token=c0b45e9d-3d0f-45b7-aef4-6681c58ba8a5", "cassette", "1", "$10"));

                orderHistory.add(order1);

                // Create some CartOrder objects for the second order
                List<CartOrder> order2 = new ArrayList<>();
                //order1.add(new CartOrder("getrichordietryin", "type1", "user1", "getrichordietryin", "Get Rich or Die Tryin", "Artist 1", "https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fgetrichordietryin.png?alt=media&token=9164229a-e780-489c-9b94-1e5873714a96", "cassette", "1", "$10"));
                order1.add(new CartOrder("GRADUATION", "type1", "user1", "getrichordietryin", "Get Rich or Die Tryin", "Artist 1", "https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fgraduation.png?alt=media&token=c0b45e9d-3d0f-45b7-aef4-6681c58ba8a5", "cassette", "1", "$10"));
                orderHistory.add(order2);


                Log.d("DEBUG THIS SHIT", orderHistory.toString());

                RecyclerView recyclerView = findViewById(R.id.order_history);
                LinearLayoutManager layoutManager = new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);

                OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(ProfileActivity.this, R.layout.order_list_item,  orderHistory, new PopularRecylcerInterface() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
                recyclerView.setAdapter(orderHistoryAdapter);
            } else {
                // Handle task failure
                Exception exception = task.getException();
                // Handle the exception accordingly
            }
        });
    }

}