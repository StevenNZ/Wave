package com.example.wave.Entities;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class Order {
    private String orderID;
    private String type;
    private String userID;



    /**
     * Constructor for Order class
     * @param orderID
     * @param type
     * @param userID
     * @param orderDate
     */
    public Order(String orderID, String type, String userID, String orderDate) {
        this.userID = userID;
        this.type = type;
        this.orderID = orderID;

    }


    /**
     * equals method for Order class
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Order) {
            Order order = (Order) obj;
            return order.getOrderID().equals(this.getOrderID());
        }
        return false;
    }

    /**
     * hashCode method for Order class
     */
    @Override
    public int hashCode() {
        return Objects.hash(orderID);
    }

    /**
     * toString method for Order class
     */
    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", userID='" + userID + '\'' +
                ", discographyFormID=" + discographyFormIDList +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                '}';
    }
}

