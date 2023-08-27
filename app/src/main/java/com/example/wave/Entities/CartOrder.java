package com.example.wave.Entities;

public class CartOrder extends Order{
    private String releaseName;
    private String artistName;
    private String releaseImage;
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
    public CartOrder(String orderID, String type, String userID, String discographyID, String releaseName, String artistName, String releaseImage, String hardMediaFormat, String quantity, String price) {
        super(orderID, type, userID, discographyID);
        this.releaseName = releaseName;
        this.artistName = artistName;
        this.releaseImage = releaseImage;
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

    /**
     * Getter for releaseName
     * @return releaseName
     */
    public String getReleaseName() {
        return releaseName;
    }

    /**
     * Setter for releaseName
     * @param releaseName
     */
    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    /**
     * Getter for artistName
     * @return artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Setter for artistName
     * @param artistName
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Getter for releaseImage
     * @return releaseImage
     */
    public String getReleaseImage() {
        return releaseImage;
    }

    /**
     * Setter for releaseImage
     * @param releaseImage
     */
    public void setReleaseImage(String releaseImage) {
        this.releaseImage = releaseImage;
    }

    /**
     * toString method for CartOrder
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + "CartOrder{" +
                "releaseName='" + releaseName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", releaseImage='" + releaseImage + '\'' +
                "hardMediaFormat='" + hardMediaFormat + '\'' +
                "quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
