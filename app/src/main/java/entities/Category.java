package entities;

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

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSubcategoriesList() {
        return subcategoriesList;
    }

    public List<String> getArtistIDList() {
        return artistIDList;
    }

    public List<String> getDiscographyIDList() {
        return discographyIDList;
    }

    public String getOrigin() {
        return origin;
    }

    public String getEra() {
        return era;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getBlurb() {
        return blurb;
    }
}

