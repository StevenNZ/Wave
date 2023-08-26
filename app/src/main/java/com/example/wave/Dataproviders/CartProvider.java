package com.example.wave.Dataproviders;

import com.example.wave.Entities.Order;
import com.example.wave.Repository.CartRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;



public interface CartProvider {
    static CartRepository getInstance() {
        return null;
    }
    Task<List<Order>>  getCart(String userID);
    void addCartItems(String userID, Order cartOrder);
    Task<List<Order>> removeFromCartByOrderID(String userID, String orderID);
    void checkoutCart(String userID);
    Task<Boolean> checkItemInCart(String userID, String orderID);
}



