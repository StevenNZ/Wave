package entities;

import java.util.List;

public class Order {
    private String orderID;
    private String userID;
    private List<String> discographyFormID;
    private String orderDate;
    private Status deliveryStatus;

    public Order(String orderID, String userID, List<String> discographyFormID, String orderDate, Status deliveryStatus) {
        this.orderID = orderID;
        this.userID = userID;
        this.discographyFormID = discographyFormID;
        this.orderDate = orderDate;
        this.deliveryStatus = deliveryStatus;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getUserID() {
        return userID;
    }

    public List<String> getDiscographyFormID() {
        return discographyFormID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public Status getDeliveryStatus() {
        return deliveryStatus;
    }
}

