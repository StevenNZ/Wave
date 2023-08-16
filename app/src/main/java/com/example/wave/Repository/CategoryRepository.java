package com.example.wave.Repository;

public class CategoryRepository {
    private static CategoryRepository instance;

    private CategoryRepository() {
    }

    public static synchronized CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }



}
