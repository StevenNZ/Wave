package com.example.wave.Domains;

import com.example.wave.Dataproviders.CartProvider;
import com.example.wave.Entities.CartOrder;
import com.example.wave.Repository.CartRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class GetCartUseCase implements CartProvider {

    @Override
    public Task<List<CartOrder>> getCart(String userID) {
        return CartRepository.getInstance().getCart(userID);
    }

    @Override
    public void addCartItems(String userID, CartOrder cartOrder) {
        CartRepository.getInstance().addCartItems(userID, cartOrder);

    }

    @Override
    public Task<List<CartOrder>> removeFromCartByOrderID(String userID, String orderID) {
        return CartRepository.getInstance().removeFromCartByOrderID(userID, orderID);
    }

    @Override
    public void checkoutCart(String userID) {
        CartRepository.getInstance().checkoutCart(userID);
    }

    @Override
    public void updateQuantityByOrderID(String userID, String orderID, String quantity) {
        CartRepository.getInstance().updateQuantityByOrderID(userID, orderID, quantity);
    }

    @Override
    public Task<Boolean> checkItemInCart(String userID, String orderID) {
        return CartRepository.getInstance().checkItemInCart(userID, orderID);
    }
}
