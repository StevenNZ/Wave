package com.example.wave.Domains;

import com.example.wave.Dataproviders.WishlistProvider;
import com.example.wave.Entities.Order;
import com.example.wave.Repository.WishlistRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class GetWishlistUseCase implements WishlistProvider {


    @Override
    public Task<List<Order>> getWishlist(String userID) {
        return WishlistRepository.getInstance().getWishlist(userID);
    }

    @Override
    public Task<Boolean> checkItemOnWishlist(String userID, String orderID) {
        return WishlistRepository.getInstance().checkItemOnWishlist(userID, orderID);
    }

    @Override
    public void appendWishlist(String userID, Order wishlistOrder) {
        WishlistRepository.getInstance().appendWishlist(userID, wishlistOrder);

    }

    @Override
    public Task<List<Order>> removeFromWishlistByOrderID(String userID, String orderID) {
        return WishlistRepository.getInstance().removeFromWishlistByOrderID(userID, orderID);
    }
}
