package com.example.wave.Entities;

import androidx.annotation.Nullable;

import java.util.List;

public class Discography {
    private String discographyID;
    private String artistID;
    private String categoryID;
    private String releaseName;
    private String releaseDate;
    private String releaseType;
    private String imageURL;
    private String cassetteImageUrl;
    private String vinylImageUrl;
    private String cdImageUrl;
    private String streamingFigures;
    private List<String> tracklist;
    private List<String> collaborators;
    private String primaryColour;
    private String secondaryColour;

    /**
     * Constructor for Discography class
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
     */
    public Discography(String discographyID, String artistID, String categoryID, String releaseName,
                        String releaseDate, String releaseType, String imageURL, String cassetteImageUrl, String vinylImageUrl, String cdImageUrl,
                        String streamingFigures, List<String> tracklist, List<String> collaborators,
                        String primaryColour, String secondaryColour) {
        this.discographyID = discographyID;
        this.artistID = artistID;
        this.categoryID = categoryID;
        this.releaseName = releaseName;
        this.releaseDate = releaseDate;
        this.releaseType = releaseType;
        this.imageURL = imageURL;
        this.cassetteImageUrl = cassetteImageUrl;
        this.vinylImageUrl = vinylImageUrl;
        this.cdImageUrl = cdImageUrl;
        this.streamingFigures = streamingFigures;
        this.tracklist = tracklist;
        this.collaborators = collaborators;
        this.primaryColour = primaryColour;
        this.secondaryColour = secondaryColour;
    }

    /**
     * Default constructor for Discography class
     */
    public Discography() {
        // Default constructor required for calls to DataSnapshot.getValue(Discography.class)
    }

    /**
     * Getter for discographyID
     * @return discographyID
     */
    public String getDiscographyID() {
        return discographyID;
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
     * Getter for releaseName
     * @return releaseName
     */
    public String getReleaseName() {
        return releaseName;
    }

    /**
     * Getter for releaseDate
     * @return releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Getter for releaseType
     * @return releaseType
     */
    public String getReleaseType() {
        return releaseType;
    }

    /**
     * Getter for imageURL
     * @return imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Getter for cassetteImageUrl
     * @return cassetteImageUrl
     */
    public String getCassetteImageUrl() {
        return cassetteImageUrl;
    }

    /**
     * Getter for vinylImageUrl
     * @return vinylImageUrl
     */
    public String getVinylImageUrl() {
        return vinylImageUrl;
    }

    /**
     * Getter for cdImageUrl
     * @return cdImageUrl
     */
    public String getCdImageUrl() {
        return cdImageUrl;
    }

    /**
     * Getter for streamingFigures
     * @return streamingFigures
     */
    public String getStreamingFigures() {
        return streamingFigures;
    }

    /**
     * Getter for tracklist
     * @return tracklist
     */
    public List<String> getTracklist() {
        return tracklist;
    }

    /**
     * Getter for collaborators
     * @return collaborators
     */
    public List<String> getCollaborators() {
        return collaborators;
    }

    /**
     * Getter for primaryColour
     * @return primaryColour
     */
    public String getPrimaryColour() {
        return primaryColour;
    }

    /**
     * Getter for secondaryColour
     * @return secondaryColour
     */
    public String getSecondaryColour() {
        return secondaryColour;
    }

    /**
     * Setter for discographyID
     * @param discographyID
     */
    public void setDiscographyID(String discographyID) {
        this.discographyID = discographyID;
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
     * Setter for releaseName
     * @param releaseName
     */
    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    /**
     * Setter for releaseDate
     * @param releaseDate
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Setter for releaseType
     * @param releaseType
     */
    public void setReleaseType(String releaseType) {
        this.releaseType = releaseType;
    }

    /**
     * Setter for imageURL
     * @param imageURL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Setter for cassetteImageUrl
     * @param cassetteImageUrl
     */
    public void setCassetteImageUrl(String cassetteImageUrl) {
        this.cassetteImageUrl = cassetteImageUrl;
    }

    /**
     * Setter for vinylImageUrl
     * @param vinylImageUrl
     */
    public void setVinylImageUrl(String vinylImageUrl) {
        this.vinylImageUrl = vinylImageUrl;
    }

    /**
     * Setter for cdImageUrl
     * @param cdImageUrl
     */
    public void setCdImageUrl(String cdImageUrl) {
        this.cdImageUrl = cdImageUrl;
    }

    /**
     * Setter for streamingFigures
     * @param streamingFigures
     */
    public void setStreamingFigures(String streamingFigures) {
        this.streamingFigures = streamingFigures;
    }

    /**
     * Setter for tracklist
     * @param tracklist
     */
    public void setTracklist(List<String> tracklist) {
        this.tracklist = tracklist;
    }

    /**
     * Setter for collaborators
     * @param collaborators
     */
    public void setCollaborators(List<String> collaborators) {
        this.collaborators = collaborators;
    }

    /**
     * Setter for primaryColour
     * @param primaryColour
     */
    public void setPrimaryColour(String primaryColour) {
        this.primaryColour = primaryColour;
    }

    /**
     * Setter for secondaryColour
     * @param secondaryColour
     */
    public void setSecondaryColour(String secondaryColour) {
        this.secondaryColour = secondaryColour;
    }

    /**
     * equals method for Discography class
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Discography) {
            Discography discography = (Discography) obj;
            return discography.getDiscographyID().equals(discographyID);
        }
        return false;
    }

    /**
     * hashCode method for Discography class
     */
    @Override
    public int hashCode() {
        return getDiscographyID().hashCode();
    }

    /**
     * toString method for Discography class
     */
    @Override
    public String toString() {
        return "Discography{" +
                "discographyID='" + discographyID + '\'' +
                ", artistID='" + artistID + '\'' +
                ", categoryID='" + categoryID + '\'' +
                ", releaseName='" + releaseName + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", releaseType='" + releaseType + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", cassetteImageUrl='" + cassetteImageUrl + '\'' +
                ", vinylImageUrl='" + vinylImageUrl + '\'' +
                ", cdImageUrl='" + cdImageUrl + '\'' +
                ", streamingFigures=" + streamingFigures +
                ", tracklist=" + tracklist +
                ", collaborators=" + collaborators +
                ", primaryColour='" + primaryColour + '\'' +
                ", secondaryColour='" + secondaryColour + '\'' +
                '}';
    }
}

