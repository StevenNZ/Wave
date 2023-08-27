package com.example.wave.Domains;

import com.example.wave.Dataproviders.OrderHistoryProvider;
import com.example.wave.Entities.CartOrder;
import com.example.wave.Repository.OrderHistoryRepository;

import java.util.List;

public class GetOrderHistoryUseCase implements OrderHistoryProvider {
    @Override
    public List<List<CartOrder>> getOrderHistory(String userID) {
        return OrderHistoryRepository.getInstance().getOrderHistory(userID);
    }

    @Override
    public void addOrder(String userID, List<CartOrder> checkoutCart) {
        OrderHistoryRepository.getInstance().addOrder(userID, checkoutCart);

    }
}
