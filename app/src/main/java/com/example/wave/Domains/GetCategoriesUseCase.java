package com.example.wave.Domains;

import android.util.Log;

import com.example.wave.Activities.CategoryBreakdown;
import com.example.wave.Activities.CategoryResultsListener;
import com.example.wave.Activities.DiscographyResultsListener;
import com.example.wave.Activities.Popular;
import com.example.wave.Dataproviders.CategoryProvider;
import com.example.wave.Entities.Category;
import com.example.wave.Entities.Discography;
import com.example.wave.Repository.CategoryRepository;
import com.example.wave.Repository.DiscographyRepository;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class GetCategoriesUseCase implements CategoryProvider {

    private final CategoryRepository categoryRepository = CategoryRepository.getInstance();


    public void getCategoryDetails(CategoryResultsListener listener){
        Task<List<Category>> categoryList = CategoryRepository.getInstance().getAllCategories();

        categoryList.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Category> list = task.getResult();
                List<CategoryBreakdown> resultCategories = new ArrayList<>();

                Log.d("SearchDebug", "from returned = " + list);

                for (Category category : list) {
                    String categoryName = category.getCategoryName();
                    String categoryImage = category.getImageURL();
                    String categoryID = category.getCategoryID();

                    CategoryBreakdown res = new CategoryBreakdown(categoryName, categoryImage, categoryID);
                    resultCategories.add(res);
                }

                // Call the listener with the populated list
                listener.onCategoryResultsReady(resultCategories);
            } else {
                Exception exception = task.getException();
                // Handle the exception
            }
        });

    };

    @Override
    public Task<List<Category>> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public Category getCategoryByID(String categoryID) {
        return categoryRepository.getCategoryByID(categoryID);
    }

}
