package com.example.wave.Activities;

import java.util.List;

public class Discography {

    private String discographyID, artistID, categoryID, releaseName, releaseDate, releaseType, releaseArtworkURI, primaryColour, secondaryColour;
    private long streamingFigures;
    private List<String> tracklist, collaborators;


    Discography(String discographyID, String artistID, String releaseName, String releaseArtworkURI){
        this.artistID = artistID;
        this.discographyID = discographyID;
        this.releaseName = releaseName;
        this.releaseArtworkURI = releaseArtworkURI;
    }

    public String getArtistID() {
        return artistID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public List<String> getTracklist() {
        return tracklist;
    }

    public String getDiscographyID() {
        return discographyID;
    }

    public long getStreamingFigures() {
        return streamingFigures;
    }

    public String getPrimaryColour() {
        return primaryColour;
    }

    public String getReleaseArtworkURI() {
        return releaseArtworkURI;
    }

    public List<String> getCollaborators() {
        return collaborators;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    public String getReleaseName() {
        return releaseName;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public String getSecondaryColour() {
        return secondaryColour;
    }

}

