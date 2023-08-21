package com.example.wave.Repository;

import android.util.Log;

import com.example.wave.Dataproviders.CategoryProvider;
import com.example.wave.Entities.Artist;
import com.example.wave.Entities.Category;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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
    public Task<List<Category>> getAllCategories() {
        return categoryCollection.get().continueWith(task -> {
            if (task.isSuccessful()) {
                List<Category> categories = new ArrayList<>();
                QuerySnapshot querySnapshot = task.getResult();
                for (QueryDocumentSnapshot document : querySnapshot) {
                    Log.d("SearchDebug", "DOCUMENT = " + document);
                    Category category = document.toObject(Category.class);
                    Log.d("SearchDebug", "CATEGORY = " + category);
                    categories.add(category);
                }
                Log.d("SearchDebug", "FROM REPO = " + categories);
                return categories;
            } else {
                throw task.getException();
            }
        });
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