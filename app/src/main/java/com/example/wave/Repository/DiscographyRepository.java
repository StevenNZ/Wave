package com.example.wave.Repository;

public class DiscographyRepository {
    private static DiscographyRepository instance;

    private DiscographyRepository() {
    }

    public static synchronized DiscographyRepository getInstance() {
        if (instance == null) {
            instance = new DiscographyRepository();
        }
        return instance;
    }

}
