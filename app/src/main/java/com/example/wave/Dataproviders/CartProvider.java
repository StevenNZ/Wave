package com.example.wave.Dataproviders;

import com.example.wave.Entities.Order;
import com.example.wave.Repository.CartRepository;

import java.util.List;



public interface CartProvider {
    CartRepository getInstance();
    List<Order> getCart();
    List<Order> removeCartItem(String discographyFormID);
    List<Order> addItemToCart(Order cartOrder);
    Order checkoutCart(String orderID);
}



