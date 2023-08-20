package com.example.wave.Entities;

import androidx.annotation.Nullable;

import java.util.List;

public class Artist {

    private  String artistID;
    private  String categoryID;
    private  String artistName;
    private  String biography;
    private  String countryOfOrigin;
    private  List<String> discographyIDList;
    private  List<String> awards;
    private  String activeYearsStart;
    private  String activeYearsEnd;
    private  String blurb;


    /**
    * Constructor for Artist class
    * @param artistID
    * @param categoryID
    * @param artistName
    * @param biography
    * @param countryOfOrigin
    * @param discographyIDList
    * @param awards
    * @param activeYearsStart
    * @param activeYearsEnd
    * @param blurb
     */
    public Artist(String artistID, String categoryID, String artistName, String biography, String countryOfOrigin,
                  List<String> discographyIDList, List<String> awards, String activeYearsStart, String activeYearsEnd,
                  String blurb) {
        this.artistID = artistID;
        this.categoryID = categoryID;
        this.artistName = artistName;
        this.biography = biography;
        this.countryOfOrigin = countryOfOrigin;
        this.discographyIDList = discographyIDList;
        this.awards = awards;
        this.activeYearsStart = activeYearsStart;
        this.activeYearsEnd = activeYearsEnd;
        this.blurb = blurb;
    }
    
    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public void setDiscographyIDList(List<String> discographyIDList) {
        this.discographyIDList = discographyIDList;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public void setActiveYearsStart(String activeYearsStart) {
        this.activeYearsStart = activeYearsStart;
    }

    public void setActiveYearsEnd(String activeYearsEnd) {
        this.activeYearsEnd = activeYearsEnd;
    }

    public void setBlurb(String blurb) {
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
     * @return artistID
     */
    public String getArtistID() {
        return artistID;
    }

    /**
     * Getter for categoryID
     * @return categoryID
     */
    public String getCategoryID() {
        return categoryID;
    }

    /**
     * Getter for artistName
     * @return artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Getter for biography
     * @return biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * Getter for countryOfOrigin
     * @return countryOfOrigin
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    /**
     * Getter for discographyIDList
     * @return discographyIDList
     */
    public List<String> getDiscographyIDList() {
        return discographyIDList;
    }

    /**
     * Getter for awards
     * @return awards
     */
    public List<String> getAwards() {
        return awards;
    }

    /**
     * Getter for activeYearsStart
     * @return activeYearsStart
     */
    public String getActiveYearsStart() {
        return activeYearsStart;
    }

    /**
     * Getter for activeYearsEnd
     * @return activeYearsEnd
     */
    public String getActiveYearsEnd() {
        return activeYearsEnd;
    }

    /**
    * Getter for blurb
    * @return blurb
    */
    public String getBlurb() {
        return blurb;
    }

    /**
     * equals method for Artist class
     */
    @Override
    public boolean equals(@Nullable Object obj){
        if (obj instanceof Artist){
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
                ", discographyIDList=" + discographyIDList +
                ", awards=" + awards +
                ", activeYearsStart='" + activeYearsStart + '\'' +
                ", activeYearsEnd='" + activeYearsEnd + '\'' +
                ", blurb='" + blurb + '\'' +
                '}';
    }
}

