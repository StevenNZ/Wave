package com.example.wave.Dataproviders;

import com.example.wave.Entities.Order;
import com.example.wave.Repository.WishlistRepository;

import java.util.List;

public interface WishlistProvider {
    WishlistRepository getInstance();
    List<Order> getWishlist();
    List<Order> appendWishlist(Order wishlistOrder);
    List<Order> removeFromWishlistByID(String discographyFormID);
}
