package com.example.wave.Dataproviders;

import com.example.wave.Entities.Category;

import java.util.List;


public interface CategoryProvider {
    CategoryRepository getInstance();
    List<Category> getAllCategories();
    Category getCategoryByID(String categoryID);
}

