package com.example.wave.Entities;

public class WishlistOrder extends Order{
    private String releaseName;
    private String artistName;
    private String releaseImage;

    /**
     * Constructor for WishlistOrder
     * @param orderID
     * @param type
     * @param userID
     * @param discographyID
     * @param releaseName
     * @param artistName
     * @param releaseImage
     */
    public WishlistOrder(String orderID, String type, String userID, String discographyID, String releaseName, String artistName, String releaseImage) {
        super(orderID, type, userID, discographyID);
        this.releaseName = releaseName;
        this.artistName = artistName;
        this.releaseImage = releaseImage;
    }

    /**
     * Empty constructor for WishlistOrder
     */
    public WishlistOrder() {
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
     * toString method for WishlistOrder
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() +
                "WishlistOrder{" +
                "releaseName='" + releaseName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", releaseImage='" + releaseImage + '\'' +
                '}';
    }
}
