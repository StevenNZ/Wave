package com.example.wave.Repository;

public class ArtistRepository {
    private static ArtistRepository instance;

    private ArtistRepository() {

    }

    public static ArtistRepository getInstance() {
        if (instance == null) {
            instance = new ArtistRepository();
        }
        return instance;
    }

}
