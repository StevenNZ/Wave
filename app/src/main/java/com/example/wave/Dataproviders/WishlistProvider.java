package com.example.wave.Dataproviders;

import com.example.wave.Entities.Order;
import com.example.wave.Entities.WishlistOrder;
import com.example.wave.Repository.WishlistRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public interface WishlistProvider {
    static WishlistRepository getInstance() {
        return null;
    }

    Task<List<WishlistOrder>> getWishlist(String userID);
    Task<Boolean> checkItemOnWishlist(String userID, String orderID);
    void appendWishlist(String userID, WishlistOrder wishlistOrder);
    Task<List<WishlistOrder>> removeFromWishlistByOrderID(String userID, String orderID);
}
