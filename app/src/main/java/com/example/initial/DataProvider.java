package com.example.initial;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<String> generateCategories(){

        List<String> categoryList = new ArrayList<String>();

        categoryList.add("pop");
        categoryList.add("kpop");
        categoryList.add("rap");

        return categoryList;
    }

    public static List<Category> getCategories(){

        List<Category> categories = new ArrayList<Category>();
        List<String> categoryList = generateCategories();

        for (String key: categoryList){
            String categoryName = key;
            String categoryImage = key + "_image";

            Category category = new Category(categoryName, categoryImage);
            categories.add(category);
        }

        return categories;

    }





}
