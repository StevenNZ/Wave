package com.example.wave.Entities;

import java.util.ArrayList;
import java.util.List;

public class KPopDiscography extends Discography{
    private String fandomName;

    private String fandomColour;

    private String managementCompany;

    /**
     * Constructor for HipHopDiscography class
     * @param discographyID
     * @param artistID
     * @param categoryID
     * @param releaseName
     * @param releaseDate
     * @param releaseType
     * @param imageURL
     * @param cassetteImageUrl
     * @param vinylImageUrl
     * @param cdImageUrl
     * @param streamingFigures
     * @param tracklist
     * @param collaborators
     * @param primaryColour
     * @param secondaryColour
     * @param fandomName
     * @param fandomColour
     * @param managementCompany
     */
    public KPopDiscography(String discographyID, String artistID, String categoryID, String releaseName, String releaseDate, String releaseType, String imageURL, String cassetteImageUrl, String vinylImageUrl, String cdImageUrl, String streamingFigures, List<String> tracklist, List<String> collaborators, String primaryColour, String secondaryColour, String fandomName, String fandomColour, String managementCompany) {
        super(discographyID, artistID, categoryID, releaseName, releaseDate, releaseType, imageURL, cassetteImageUrl, vinylImageUrl, cdImageUrl, streamingFigures, tracklist, collaborators, primaryColour, secondaryColour);
        this.fandomName = fandomName;
        this.fandomColour = fandomColour;
        this.managementCompany = managementCompany;
    }

    /**
     * Default constructor for HipHopDiscography class
     */
    public KPopDiscography() {

    }

    /**
     * Getter for fandomName
     * @return fandomName
     */
    public String getFandomName() {
        return fandomName;
    }

    /**
     * Setter for fandomName
     * @param fandomName
     */
    public void setFandomName(String fandomName) {
        this.fandomName = fandomName;
    }

    /**
     * Getter for fandomColour
     * @return fandomColour
     */
    public String getFandomColour() {
        return fandomColour;
    }

    /**
     * Setter for fandomColour
     * @param fandomColour
     */
    public void setFandomColour(String fandomColour) {
        this.fandomColour = fandomColour;
    }

    /**
     * Getter for managementCompany
     * @return managementCompany
     */
    public String getManagementCompany() {
        return managementCompany;
    }

    /**
     * Setter for managementCompany
     * @param managementCompany
     */
    public void setManagementCompany(String managementCompany) {
        this.managementCompany = managementCompany;
    }

    /**
     * Getter for tags
     * @return tags
     */
    @Override
    public List<String> getTags() {
        List<String> tags = new ArrayList<>();
        tags.add(fandomName);
        tags.add(fandomColour);
        tags.add(managementCompany);
        return tags;
    }
}
