package com.example.wave.Repository;

public class OrderHistoryRepository {

private static OrderHistoryRepository instance;

    private OrderHistoryRepository() {
    }

    public static synchronized OrderHistoryRepository getInstance() {
        if (instance == null) {
            instance = new OrderHistoryRepository();
        }
        return instance;
    }

}
