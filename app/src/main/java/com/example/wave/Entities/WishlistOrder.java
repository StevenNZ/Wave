package com.example.wave.Entities;

public class WishlistOrder extends Order{
    private String releaseName;
    private String artistName;
    private String releaseImage;

    public WishlistOrder(String orderID, String type, String userID, String discographyID, String releaseName, String artistName, String releaseImage) {
        super(orderID, type, userID, discographyID);
        this.releaseName = releaseName;
        this.artistName = artistName;
        this.releaseImage = releaseImage;
    }

    public WishlistOrder() {
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getReleaseImage() {
        return releaseImage;
    }

    public void setReleaseImage(String releaseImage) {
        this.releaseImage = releaseImage;
    }

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
