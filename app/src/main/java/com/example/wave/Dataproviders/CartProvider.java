package com.example.wave.Dataproviders;

import com.example.wave.Entities.Order;
import com.example.wave.Repository.CartRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;



public interface CartProvider {
    static CartRepository getInstance() {
        return null;
    }

    void createCart(String userID);
    Task<Order> getCart(String userID);
    void addCartItems(String userID, String discographyFormID);
    void removeCartItems(String userID, String discographyFormID);
    void checkoutCart(String userID);
}



