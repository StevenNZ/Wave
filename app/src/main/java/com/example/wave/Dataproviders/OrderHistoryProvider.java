package com.example.wave.Dataproviders;

import com.example.wave.Entities.Order;
import com.example.wave.Repository.OrderHistoryRepository;

import java.util.List;

public interface OrderHistoryProvider {
    OrderHistoryRepository getInstance();
    List<List<Order>> getOrderHistory();
    List<Order> getOrder(String orderID);
    List<Order> addOrder(Order checkoutCart);
    List<Order> cancelOrder(String orderID);
}

