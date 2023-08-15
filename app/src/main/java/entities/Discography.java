package entities;

import java.util.List;

public class Discography {
    private String discographyID;
    private String artistID;
    private String categoryID;
    private String releaseName;
    private String releaseDate;
    private String releaseType;
    private String releaseArtworkURI;
    private long streamingFigures;
    private List<String> tracklist;
    private List<String> collaborators;
    private String primaryColour;
    private String secondaryColour;

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

    public String getDiscographyID() {
        return discographyID;
    }

    public String getArtistID() {
        return artistID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public String getReleaseArtworkURI() {
        return releaseArtworkURI;
    }

    public long getStreamingFigures() {
        return streamingFigures;
    }

    public List<String> getTracklist() {
        return tracklist;
    }

    public List<String> getCollaborators() {
        return collaborators;
    }

    public String getPrimaryColour() {
        return primaryColour;
    }

    public String getSecondaryColour() {
        return secondaryColour;
    }
}

