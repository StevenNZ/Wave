package com.example.wave.Dataproviders;

import com.example.wave.Entities.Artist;
import com.example.wave.Entities.Category;
import com.example.wave.Repository.CategoryRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;


public interface CategoryProvider {
    static CategoryRepository getInstance() {
        return null;
    }

    Task<List<Category>> getAllCategories();
    Category getCategoryByID(String categoryID);
}

