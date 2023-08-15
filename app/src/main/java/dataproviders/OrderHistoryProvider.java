package dataproviders;

import java.util.List;

import entities.Order;

public interface OrderHistoryProvider {
    OrderHistoryRepository getInstance();
    List<List<Order>> getOrderHistory();
    List<Order> getOrder(String orderID);
    List<Order> addOrder(Order checkoutCart);
    List<Order> cancelOrder(String orderID);
}

