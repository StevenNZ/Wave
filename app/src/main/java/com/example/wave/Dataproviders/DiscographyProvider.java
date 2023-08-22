package com.example.wave.Dataproviders;

import com.example.wave.Entities.Discography;
import com.example.wave.Repository.DiscographyRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;


public interface DiscographyProvider {
    static DiscographyRepository getInstance() {
        return null;
    }

    Task<List<Discography>> getAllDiscography();
    Task<Discography> getDiscographyByDiscographyID(String discographyID);
    Task<List<Discography>> getDiscographyBySearch(String searchString);
    Task<List<Discography>> getDiscographyByCategoryID(String categoryID);
    Task<List<Discography>> getDiscographyByArtistID(String artistID);
}

