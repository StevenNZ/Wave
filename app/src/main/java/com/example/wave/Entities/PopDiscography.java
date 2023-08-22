package com.example.wave.Entities;

import java.util.ArrayList;
import java.util.List;

public class PopDiscography extends Discography{
    private String bPM;
    private String remixName;
    private String emotion;
    private String chartTop;
    private String ChartTopSong;

    /**
     * Constructor for PopDiscography class
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
     * @param bPM
     * @param remixName
     * @param emotion
     * @param chartTop
     * @param chartTopSong
     */
    public PopDiscography(String discographyID, String artistID, String categoryID, String releaseName, String releaseDate, String releaseType, String imageURL, String cassetteImageUrl, String vinylImageUrl, String cdImageUrl, String streamingFigures, List<String> tracklist, List<String> collaborators, String primaryColour, String secondaryColour, String bPM, String remixName, String emotion, String chartTop, String chartTopSong) {
        super(discographyID, artistID, categoryID, releaseName, releaseDate, releaseType, imageURL, cassetteImageUrl, vinylImageUrl, cdImageUrl, streamingFigures, tracklist, collaborators, primaryColour, secondaryColour);
        this.bPM = bPM;
        this.remixName = remixName;
        this.emotion = emotion;
        this.chartTop = chartTop;
        ChartTopSong = chartTopSong;
    }

    /**
     * Default Constructor for PopDiscography class
     */
    public PopDiscography() {

    }

    /**
     * Getter for bPM
     * @return bPM
     */
    public String getbPM() {
        return bPM;
    }

    /**
     * Setter for bPM
     * @param bPM
     */
    public void setbPM(String bPM) {
        this.bPM = bPM;
    }

    /**
     * Getter for remixName
     * @return remixName
     */
    public String getRemixName() {
        return remixName;
    }

    /**
     * Setter for remixName
     * @param remixName
     */
    public void setRemixName(String remixName) {
        this.remixName = remixName;
    }

    /**
     * Getter for emotion
     * @return
     */
    public String getEmotion() {
        return emotion;
    }

    /**
     * Setter for emotion
     * @param emotion
     */
    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    /**
     * Getter for chartTop
     * @return
     */
    public String getChartTop() {
        return chartTop;
    }

    /**
     * Setter for chartTop
     * @param chartTop
     */
    public void setChartTop(String chartTop) {
        this.chartTop = chartTop;
    }

    /**
     * Getter for ChartTopSong
     * @return
     */
    public String getChartTopSong() {
        return ChartTopSong;
    }

    /**
     * Setter for ChartTopSong
     * @param chartTopSong
     */
    public void setChartTopSong(String chartTopSong) {
        ChartTopSong = chartTopSong;
    }

    /**
     * Getter for tags
     * @return tags
     */
    @Override
    public List<String> getTags() {
        List<String> tags = new ArrayList<>();
        tags.add(getbPM());
        tags.add(getRemixName());
        tags.add(getEmotion());
        tags.add(getChartTop());
        tags.add(getChartTopSong());
        return tags;
    }

    /**
     * toString method for PopDiscography class
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + "PopDiscography{" +
                "bPM='" + bPM + '\'' +
                ", remixName='" + remixName + '\'' +
                ", emotion='" + emotion + '\'' +
                ", chartTop='" + chartTop + '\'' +
                ", ChartTopSong='" + ChartTopSong + '\'' +
                '}';
    }
}
