package com.example.wave.Dataproviders;

import com.example.wave.Entities.CartOrder;
import com.example.wave.Entities.Order;
import com.example.wave.Repository.OrderHistoryRepository;

import java.util.List;

public interface OrderHistoryProvider {
    static OrderHistoryRepository getInstance() {
        return null;
    }

    List<List<CartOrder>> getOrderHistory(String userID);
    void addOrder(String userID, List<CartOrder> checkoutCart);
}

