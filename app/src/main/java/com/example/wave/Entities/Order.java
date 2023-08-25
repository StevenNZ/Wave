package com.example.wave.Entities;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class Order {
    private String orderID;
    private String type;
    private String userID;

    private String discographyID;



    /**
     * Constructor for Order class
     * @param orderID
     * @param type
     * @param userID
     * @param discographyID
     */
    public Order(String orderID, String type, String userID, String discographyID) {
        this.userID = userID;
        this.type = type;
        this.orderID = orderID;
        this.discographyID = discographyID;

    }

    /**
     * Default constructor for Order class
     */
    public Order() {
    }

    /**
     * Getter for orderID
     * @return orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * Setter for orderID
     * @param orderID
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    /**
     * Getter for type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for userID
     * @return userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Setter for userID
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Getter for discographyID
     * @return discographyID
     */
    public String getDiscographyID() {
        return discographyID;
    }

    /**
     * Setter for discographyID
     * @param discographyID
     */
    public void setDiscographyID(String discographyID) {
        this.discographyID = discographyID;
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
                ", type='" + type + '\'' +
                ", discographyID='" + discographyID + '\'' +
                '}';
    }
}

