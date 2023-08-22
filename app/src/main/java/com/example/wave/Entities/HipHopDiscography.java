package com.example.wave.Entities;

import java.util.ArrayList;
import java.util.List;

public class HipHopDiscography extends Discography {
    private boolean explictContent;
    private String subCategory;
    private String producerTag;

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
     * @param explictContent
     * @param subCategory
     * @param producerTag
     */
    public HipHopDiscography(String discographyID, String artistID, String categoryID, String releaseName, String releaseDate, String releaseType, String imageURL, String cassetteImageUrl, String vinylImageUrl, String cdImageUrl, String streamingFigures, List<String> tracklist, List<String> collaborators, String primaryColour, String secondaryColour, boolean explictContent, String subCategory, String producerTag, int views) {
        super(discographyID, artistID, categoryID, releaseName, releaseDate, releaseType, imageURL, cassetteImageUrl, vinylImageUrl, cdImageUrl, streamingFigures, tracklist, collaborators, primaryColour, secondaryColour, views);
        this.explictContent = explictContent;
        this.subCategory = subCategory;
        this.producerTag = producerTag;
    }

    /**
     * Default Constructor for HipHopDiscography class
     */
    public HipHopDiscography() {

    }

    /**
     * Getter for explictContent
     * @return
     */
    public boolean getExplictContent() {
        return explictContent;
    }

    /**
     * Getter for explictContent
     * @return
     */
    public String getExplictContentString() {
        if (explictContent) {
            return "Explicit";
        } else {
            return "Clean";
        }
    }

    /**
     * Setter for explictContent
     * @param explictContent
     */
    public void setExplictContent(boolean explictContent) {
        this.explictContent = explictContent;
    }

    /**
     * Getter for subCategory
     * @return subCategory
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * Setter for subCategory
     * @param subCategory
     */
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    /**
     * Getter for producerTag
     * @return producerTag
     */
    public String getProducerTag() {
        return producerTag;
    }

    /**
     * Setter for producerTag
     * @param producerTag
     */
    public void setProducerTag(String producerTag) {
        this.producerTag = producerTag;
    }

    /**
     * Getter for tags of discography
     * @return tags
     */
    @Override
    public List<String> getTags(){
        List<String> tags = new ArrayList<>();
        tags.add(getSubCategory());
        tags.add(getProducerTag());
        tags.add(getExplictContentString());
        return tags;
    }

    /**
     * toString method for HipHopDiscography class
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + "HipHopDiscography{" +
                "explictContent=" + explictContent +
                ", subCategory='" + subCategory + '\'' +
                ", producerTag='" + producerTag + '\'' +
                '}';
    }
}
