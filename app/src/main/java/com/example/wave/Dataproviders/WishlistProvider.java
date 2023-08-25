package com.example.wave.Dataproviders;

import com.example.wave.Entities.Order;
import com.example.wave.Repository.WishlistRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public interface WishlistProvider {
    static WishlistRepository getInstance() {
        return null;
    }

    Task<List<Order>> getWishlist(String userID);
    Task<Boolean> checkItemOnWishlist(String userID, String orderID);
    void appendWishlist(String userID, Order wishlistOrder);
    Task<List<Order>> removeFromWishlistByOrderID(String userID, String orderID);
}
