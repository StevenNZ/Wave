package com.example.wave.Entities;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class Order {
    private final String orderID;
    private final String userID;
    private List<String> discographyFormIDList;
    private String orderDate;
    private final Status deliveryStatus;

    /**
     * Constructor for Order class
     * @param orderID
     * @param userID
     * @param discographyFormID
     * @param orderDate
     */
    public Order(String orderID, String userID, List<String> discographyFormID, String orderDate) {
        this.orderID = orderID;
        this.userID = userID;
        this.discographyFormIDList = discographyFormID;
        this.orderDate = orderDate;
        this.deliveryStatus = Status.Ordered;
    }

    /**
     * Getter for orderID
     * @return orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * Getter for userID
     * @return userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Getter for discographyFormID
     * @return discographyFormID
     */
    public List<String> getDiscographyFormIDList() {
        return discographyFormIDList;
    }

    public void appendDiscographyFormIDList(String discographyFormID) {
        this.discographyFormIDList.add(discographyFormID);
    }
    public void removeDiscographyFormIDList(String discographyFormID) {
        this.discographyFormIDList.remove(discographyFormID);
    }

    /**
     * Getter for orderDate
     * @return orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * Getter for deliveryStatus
     * @return deliveryStatus
     */
    public Status getDeliveryStatus() {
        return deliveryStatus;
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

