package com.example.wave.Entities;

public class CartOrder extends Order{
    private String quantity;

    private String hardMediaFormat;

    private String price;

    /**
     * Constructor for CartOrder class
     * @param orderID
     * @param type
     * @param userID
     * @param discographyID
     * @param hardMediaFormat
     * @param quantity
     * @param price
     */
    public CartOrder(String orderID, String type, String userID, String discographyID, String hardMediaFormat, String quantity, String price) {
        super(orderID, type, userID, discographyID);
        this.quantity = quantity;
        this.price = price;
        this.hardMediaFormat = hardMediaFormat;
    }

    /**
     * Default constructor for CartOrder class
     */
    public CartOrder() {
        super();
    }

    /**
     * Getter for quantity
     * @return quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Setter for quantity
     * @param quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter for price
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Getter for hardMediaFormat
     * @return hardMediaFormat
     */
    public String getHardMediaFormat() {
        return hardMediaFormat;
    }
    /**
     * Setter for hardMediaFormat
     * @param hardMediaFormat
     */
    public void setHardMediaFormat(String hardMediaFormat) {
        this.hardMediaFormat = hardMediaFormat;
    }

    @Override
    public String toString() {
        return super.toString() + "CartOrder{" +
                "hardMediaFormat='" + hardMediaFormat + '\'' +
                "quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
