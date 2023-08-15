package entities;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class Order {
    private String orderID;
    private String userID;
    private List<String> discographyFormID;
    private String orderDate;
    private Status deliveryStatus;

    /**
     * Constructor for Order class
     * @param orderID
     * @param userID
     * @param discographyFormID
     * @param orderDate
     * @param deliveryStatus
     */
    public Order(String orderID, String userID, List<String> discographyFormID, String orderDate, Status deliveryStatus) {
        this.orderID = orderID;
        this.userID = userID;
        this.discographyFormID = discographyFormID;
        this.orderDate = orderDate;
        this.deliveryStatus = deliveryStatus;
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
    public List<String> getDiscographyFormID() {
        return discographyFormID;
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
                ", discographyFormID=" + discographyFormID +
                ", orderDate='" + orderDate + '\'' +
                ", deliveryStatus=" + deliveryStatus +
                '}';
    }
}

