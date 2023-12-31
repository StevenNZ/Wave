package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wave.Adaptor.CartAdaptor;
import com.example.wave.Domains.AuthenticationUserUseCase;
import com.example.wave.Domains.GetCartUseCase;
import com.example.wave.Domains.GetOrderHistoryUseCase;
import com.example.wave.Entities.CartOrder;
import com.example.wave.Entities.Order;
import com.example.wave.R;
import com.example.wave.ViewModel.AuthenticationViewModel;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CartActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RelativeLayout relativeLayout;
    private List<CartOrder> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        setBottomNavBar();
        showCart();

        Button checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(v -> {
            if (!cart.isEmpty()){
                AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
                String userID = authenticationUserUseCase.getUserID();
                GetCartUseCase getCartUseCase = new GetCartUseCase();
                getCartUseCase.checkoutCart(userID);
                this.showEmptyView();
            }
        });
    }

    private void setBottomNavBar() {
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

    private Task<List<CartOrder>> getCartOrders() {
        AuthenticationViewModel authenticationViewModel = new AuthenticationViewModel();
        GetCartUseCase getCartUseCase = new GetCartUseCase();
        return getCartUseCase.getCart(authenticationViewModel.getUserID());
    }

    private void showCart() {
        getCartOrders()
                .addOnSuccessListener(cartOrders -> {
                    cart = cartOrders;
                    TextView cartTotal = findViewById(R.id.cartTotal);
                    relativeLayout = findViewById(R.id.cartEmptyLayout);
                    CartAdaptor cartAdaptor = new CartAdaptor(this, R.layout.cart_list_item, cart, this::onItemClick, cartTotal, relativeLayout, recyclerView);

                    if(cart.isEmpty()){
                        this.showEmptyView();

                    }else{
                        relativeLayout.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(cartAdaptor);
                    }
                });


    }

    private void showEmptyView() {
        relativeLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }


    private void onItemClick(int position) {
        Order currentItem = cart.get(position);
        openDiscographyDetail(currentItem);
    }

    private void openDiscographyDetail(Order order) {
        Intent intent = new Intent(CartActivity.this, DiscographyDetailActivity.class);
        intent.putExtra("DiscographyId", order.getDiscographyID());
        intent.putExtra("ArtistName", order.getOrderID());
        startActivity(intent);
    }

}