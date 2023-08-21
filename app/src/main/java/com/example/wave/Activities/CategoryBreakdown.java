package com.example.wave.Activities;

public class CategoryBreakdown {

    private String categoryName, categoryImage, categoryID;

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public CategoryBreakdown(String categoryName, String categoryImage, String categoryID){
        this.categoryID = categoryID;
        this.categoryImage = categoryImage;
        this.categoryName = categoryName;
    }
}

