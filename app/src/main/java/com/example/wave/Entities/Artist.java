package com.example.wave.Entities;

import androidx.annotation.Nullable;

import java.util.List;

public class Artist {

    private String artistID;
    private String categoryID;
    private String artistName;
    private String biography;
    private String countryOfOrigin;
    private String imageURL;
    private List<String> discographyIDList;
    private List<String> awards;
    private String activeYearsStart;
    private String activeYearsEnd;
    private String blurb;

    /**
     * Constructor for Artist class
     *
     * @param artistID
     * @param categoryID
     * @param artistName
     * @param biography
     * @param countryOfOrigin
     * @param imageURL
     * @param discographyIDList
     * @param awards
     * @param activeYearsStart
     * @param activeYearsEnd
     * @param blurb
     */
    public Artist(String artistID, String categoryID, String artistName, String biography, String countryOfOrigin, String imageURL,
                  List<String> discographyIDList, List<String> awards, String activeYearsStart, String activeYearsEnd,
                  String blurb) {
        this.artistID = artistID;
        this.categoryID = categoryID;
        this.artistName = artistName;
        this.biography = biography;
        this.countryOfOrigin = countryOfOrigin;
        this.imageURL = imageURL;
        this.discographyIDList = discographyIDList;
        this.awards = awards;
        this.activeYearsStart = activeYearsStart;
        this.activeYearsEnd = activeYearsEnd;
        this.blurb = blurb;
    }

    /**
     * Default constructor for Artist class
     */
    public Artist() {
        // Default constructor required for calls to DataSnapshot.getValue(Artist.class)
    }

    /**
     * Getter for artistID
     *
     * @return artistID
     */
    public String getArtistID() {
        return artistID;
    }

    /**
     * Getter for categoryID
     *
     * @return categoryID
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     * Getter for artistName
     *
     * @return artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Getter for biography
     *
     * @return biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * Getter for countryOfOrigin
     *
     * @return countryOfOrigin
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * Getter for imageURL
     *
     * @return imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Getter for discographyIDList
     *
     * @return discographyIDList
     */
    public List<String> getDiscographyIDList() {
        return discographyIDList;
    }

    /**
     * Getter for awards
     *
     * @return awards
     */
    public List<String> getAwards() {
        return awards;
    }

    /**
     * Getter for activeYearsStart
     *
     * @return activeYearsStart
     */
    public String getActiveYearsStart() {
        return activeYearsStart;
    }

    /**
     * Getter for activeYearsEnd
     *
     * @return activeYearsEnd
     */
    public String getActiveYearsEnd() {
        return activeYearsEnd;
    }

    /**
     * Getter for blurb
     *
     * @return blurb
     */
    public String getBlurb() {
        return blurb;
    }

    /**
     * Setter for artistID
     * @param artistID
     */
    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    /**
     * Setter for categoryID
     * @param categoryID
     */
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * Setter for artistName
     * @param artistName
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Setter for biography
     * @param biography
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * Setter for countryOfOrigin
     * @param countryOfOrigin
     */
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    /**
     * Setter for discographyIDList
     * @param discographyIDList
     */
    public void setDiscographyIDList(List<String> discographyIDList) {
        this.discographyIDList = discographyIDList;
    }

    /**
     * Setter for awards
     * @param awards
     */
    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    /**
     * Setter for activeYearsStart
     * @param activeYearsStart
     */
    public void setActiveYearsStart(String activeYearsStart) {
        this.activeYearsStart = activeYearsStart;
    }

    /**
     * Setter for activeYearsEnd
     * @param activeYearsEnd
     */
    public void setActiveYearsEnd(String activeYearsEnd) {
        this.activeYearsEnd = activeYearsEnd;
    }

    /**
     * Setter for imageURL
     * @param imageURL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Setter for blurb
     * @param blurb
     */
    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    /**
     * equals method for Artist class
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Artist) {
            Artist artist = (Artist) obj;
            return artistID.equals(artist.getArtistID());
        }
        return false;
    }

    /**
     * hashCode method for Artist class
     */
    @Override
    public int hashCode() {
        return getArtistID().hashCode();
    }

    /**
     * toString method for Artist class
     */
    @Override
    public String toString() {
        return "Artist{" +
                "artistID='" + artistID + '\'' +
                ", categoryID='" + categoryID + '\'' +
                ", artistName='" + artistName + '\'' +
                ", biography='" + biography + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", discographyIDList=" + discographyIDList +
                ", awards=" + awards +
                ", activeYearsStart='" + activeYearsStart + '\'' +
                ", activeYearsEnd='" + activeYearsEnd + '\'' +
                ", blurb='" + blurb + '\'' +
                '}';
    }
}

