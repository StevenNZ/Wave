package com.example.wave.Activities;

public class Category {


    String categoryImage, categoryName;

    public String getCategoryImage() {
        return categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Category(String categoryName, String categoryImage){

        this.categoryImage = categoryImage;
        this.categoryName = categoryName;

    }
}
