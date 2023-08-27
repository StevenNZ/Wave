package com.example.wave.Domains;

import android.util.Log;

import com.example.wave.Dataproviders.OrderHistoryProvider;
import com.example.wave.Entities.CartOrder;
import com.example.wave.Repository.OrderHistoryRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class GetOrderHistoryUseCase implements OrderHistoryProvider {
    @Override
    public Task<List<List<CartOrder>>> getOrderHistory(String userID) {
        return OrderHistoryRepository.getInstance().getOrderHistory(userID);
    }

    @Override
    public void addOrder(String userID, List<CartOrder> checkoutCart) {
        OrderHistoryRepository.getInstance().addOrder(userID, checkoutCart);

    }
}
