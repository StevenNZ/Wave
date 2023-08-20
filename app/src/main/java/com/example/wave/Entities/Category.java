package com.example.wave.Entities;

import androidx.annotation.Nullable;

import java.util.List;

public class Category {
    private String categoryID;
    private String categoryName;
    private String description;
    private List<String> subcategoriesList;
    private List<String> artistIDList;
    private List<String> discographyIDList;
    private String origin;
    private String era;
    private String imageURL;
    private String blurb;

    /**
     * Constructor for Category class
     *
     * @param categoryID
     * @param categoryName
     * @param description
     * @param subcategoriesList
     * @param artistIDList
     * @param discographyIDList
     * @param origin
     * @param era
     * @param imageURL
     * @param blurb
     */
    public Category(String categoryID, String categoryName, String description, List<String> subcategoriesList,
                    List<String> artistIDList, List<String> discographyIDList, String origin, String era,
                    String imageURL, String blurb) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
        this.subcategoriesList = subcategoriesList;
        this.artistIDList = artistIDList;
        this.discographyIDList = discographyIDList;
        this.origin = origin;
        this.era = era;
        this.imageURL = imageURL;
        this.blurb = blurb;
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
     * Getter for categoryName
     *
     * @return categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Getter for description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for subcategoriesList
     *
     * @return subcategoriesList
     */
    public List<String> getSubcategoriesList() {
        return subcategoriesList;
    }

    /**
     * Getter for artistIDList
     *
     * @return artistIDList
     */
    public List<String> getArtistIDList() {
        return artistIDList;
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
     * Getter for origin
     *
     * @return origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Getter for era
     *
     * @return era
     */
    public String getEra() {
        return era;
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
     * Getter for blurb
     *
     * @return blurb
     */
    public String getBlurb() {
        return blurb;
    }

    /**
     * Setter for categoryID
     * @param categoryID
     */
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * Setter for categoryName
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Setter for description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for subcategoriesList
     * @param subcategoriesList
     */
    public void setSubcategoriesList(List<String> subcategoriesList) {
        this.subcategoriesList = subcategoriesList;
    }

    /**
     * Setter for artistIDList
     * @param artistIDList
     */
    public void setArtistIDList(List<String> artistIDList) {
        this.artistIDList = artistIDList;
    }

    /**
     * Setter for discographyIDList
     * @param discographyIDList
     */
    public void setDiscographyIDList(List<String> discographyIDList) {
        this.discographyIDList = discographyIDList;
    }

    /**
     * Setter for origin
     * @param origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Setter for era
     * @param era
     */
    public void setEra(String era) {
        this.era = era;
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
     * equals method for Category class
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Category) {
            Category category = (Category) obj;
            return categoryID.equals(category.getCategoryID());
        }
        return false;
    }


    /**
     * hashCode method for Category class
     */
    @Override
    public int hashCode() {
        return getCategoryID().hashCode();
    }

    /**
     * toString method for Category class
     */
    @Override
    public String toString() {
        return "Category{" +
                "categoryID='" + categoryID + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", subcategoriesList=" + subcategoriesList +
                ", artistIDList=" + artistIDList +
                ", discographyIDList=" + discographyIDList +
                ", origin='" + origin + '\'' +
                ", era='" + era + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", blurb='" + blurb + '\'' +
                '}';
    }
}

