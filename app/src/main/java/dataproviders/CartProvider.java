package dataproviders;

import java.util.List;

import entities.Order;

public interface CartProvider {
    CartRepository getInstance();
    List<Order> getCart();
    List<Order> removeCartItem(String discographyFormID);
    List<Order> addItemToCart(Order cartOrder);
    Order checkoutCart(String orderID);
}



