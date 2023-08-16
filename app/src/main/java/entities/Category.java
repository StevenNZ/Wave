package entities;

import androidx.annotation.Nullable;

import java.util.List;

public class Category {
    final private String categoryID;
    final private String categoryName;
    final private String description;
    final private List<String> subcategoriesList;
    final private List<String> artistIDList;
    final private List<String> discographyIDList;
    final private String origin;
    final private String era;
    final private String imageURL;
    final private String blurb;

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

