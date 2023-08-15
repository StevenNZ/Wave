package dataproviders;

import java.util.List;

import entities.Order;

public interface WishlistProvider {
    WishlistRepository getInstance();
    List<Order> getWishlist();
    List<Order> appendWishlist(Order wishlistOrder);
    List<Order> removeFromWishlistByID(String discographyFormID);
}
