package entities;

import androidx.annotation.Nullable;

import java.util.List;

public class Discography {
    private final String discographyID;
    private final String artistID;
    private final String categoryID;
    private final String releaseName;
    private final String releaseDate;
    private final String releaseType;
    private final String releaseArtworkURI;
    private final long streamingFigures;
    private final List<String> tracklist;
    private final List<String> collaborators;
    private final String primaryColour;
    private final String secondaryColour;

    /**
     * Constructor for Discography class
     * @param discographyID
     * @param artistID
     * @param categoryID
     * @param releaseName
     * @param releaseDate
     * @param releaseType
     * @param releaseArtworkURI
     * @param streamingFigures
     * @param tracklist
     * @param collaborators
     * @param primaryColour
     * @param secondaryColour
     */
    public Discography(String discographyID, String artistID, String categoryID, String releaseName,
                        String releaseDate, String releaseType, String releaseArtworkURI,
                        long streamingFigures, List<String> tracklist, List<String> collaborators,
                        String primaryColour, String secondaryColour) {
        this.discographyID = discographyID;
        this.artistID = artistID;
        this.categoryID = categoryID;
        this.releaseName = releaseName;
        this.releaseDate = releaseDate;
        this.releaseType = releaseType;
        this.releaseArtworkURI = releaseArtworkURI;
        this.streamingFigures = streamingFigures;
        this.tracklist = tracklist;
        this.collaborators = collaborators;
        this.primaryColour = primaryColour;
        this.secondaryColour = secondaryColour;
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
     * Getter for releaseArtworkURI
     * @return releaseArtworkURI
     */
    public String getReleaseArtworkURI() {
        return releaseArtworkURI;
    }

    /**
     * Getter for streamingFigures
     * @return streamingFigures
     */
    public long getStreamingFigures() {
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
                ", releaseArtworkURI='" + releaseArtworkURI + '\'' +
                ", streamingFigures=" + streamingFigures +
                ", tracklist=" + tracklist +
                ", collaborators=" + collaborators +
                ", primaryColour='" + primaryColour + '\'' +
                ", secondaryColour='" + secondaryColour + '\'' +
                '}';
    }
}

