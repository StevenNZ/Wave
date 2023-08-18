package com.example.wave.Repository;

import com.example.wave.Dataproviders.CategoryProvider;
import com.example.wave.Entities.Category;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CategoryRepository implements CategoryProvider {
    private static CategoryRepository instance;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference categoryCollection = db.collection("Category");

    private CategoryRepository() {
    }

    /**
     * Singleton pattern
     *
     * @return instance of CategoryRepository
     */
    public static synchronized CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }


    /**
     * Gets all categories from the database
     *
     * @return List<Category> with all categories
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryCollection.get().continueWith(task -> {
            if (task.isSuccessful()) {
                List<Category> categories = task.getResult().toObjects(Category.class);
                return categories;
            } else {
                throw task.getException();
            }
        }).getResult();
    }

    /**
     * Gets a category by a category ID
     *
     * @param categoryID
     * @return Category entity with the details about the category
     */
    @Override
    public Category getCategoryByID(String categoryID) {
        return categoryCollection.document(categoryID).get().continueWith(task -> {
            if (task.isSuccessful()) {
                Category category = task.getResult().toObject(Category.class);
                return category;
            } else {
                throw task.getException();
            }
        }).getResult();
    }


}