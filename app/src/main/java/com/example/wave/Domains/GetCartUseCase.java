package com.example.wave.Domains;

import com.example.wave.Dataproviders.CartProvider;
import com.example.wave.Entities.Order;
import com.example.wave.Repository.CartRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class GetCartUseCase implements CartProvider {

    @Override
    public Task<List<Order>> getCart(String userID) {
        return CartRepository.getInstance().getCart(userID);
    }

    @Override
    public void addCartItems(String userID, Order cartOrder) {
        CartRepository.getInstance().addCartItems(userID, cartOrder);

    }

    @Override
    public Task<List<Order>> removeFromCartByOrderID(String userID, String orderID) {
        return CartRepository.getInstance().removeFromCartByOrderID(userID, orderID);
    }

    @Override
    public void checkoutCart(String userID) {
        CartRepository.getInstance().checkoutCart(userID);
    }

    @Override
    public Task<Boolean> checkItemInCart(String userID, String orderID) {
        return CartRepository.getInstance().checkItemInCart(userID, orderID);
    }
}
