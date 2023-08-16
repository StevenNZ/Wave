package com.example.wave.Entities;

import androidx.annotation.Nullable;

public class DiscographyForm {
    private final String discographyFormID;
    private final String discographyID;
    private final String name;
    private final String price;
    private final Format hardMediaFormat;

    /**
     * Constructor for DiscographyForm class
     * @param discographyFormID
     * @param discographyID
     * @param name
     * @param price
     * @param hardMediaFormat
     */
    public DiscographyForm(String discographyFormID, String discographyID, String name, String price, Format hardMediaFormat) {
        this.discographyFormID = discographyFormID;
        this.discographyID = discographyID;
        this.name = name;
        this.price = price;
        this.hardMediaFormat = hardMediaFormat;
    }

    /**
     * Getter for discographyFormID
     * @return discographyFormID
     */
    public String getDiscographyFormID() {
        return discographyFormID;
    }

    /**
     * Getter for discographyID
     * @return discographyID
     */
    public String getDiscographyID() {
        return discographyID;
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for price
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Getter for hardMediaFormat
     * @return hardMediaFormat
     */
    public Format getHardMediaFormat() {
        return hardMediaFormat;
    }

    /**
     * equals method for DiscographyForm class
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof DiscographyForm) {
            DiscographyForm discographyForm = (DiscographyForm) obj;
            return discographyForm.getDiscographyFormID().equals(discographyFormID);
        }
        return false;
    }

    /**
     * hashCode method for DiscographyForm class
     */
    @Override
    public int hashCode() {
        return discographyFormID.hashCode();
    }

    /**
     * toString method for DiscographyForm class
     */
    @Override
    public String toString() {
        return "DiscographyForm{" +
                "discographyFormID='" + discographyFormID + '\'' +
                ", discographyID='" + discographyID + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", hardMediaFormat=" + hardMediaFormat +
                '}';
    }

}
