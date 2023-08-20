package com.example.wave.Dataproviders;

import com.example.wave.Entities.Discography;
import com.example.wave.Entities.DiscographyForm;
import com.example.wave.Repository.DiscographyRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;


public interface DiscographyProvider {
    static DiscographyRepository getInstance() {
        return null;
    }

    Task<List<Discography>> getAllDiscography(String discographyID);
    Task<Discography> getDiscographyByDiscographyID(String discographyID);
    Task<List<Discography>> getDiscographyBySearch(String searchString);
    Task<Discography> getDiscographyByCategoryID(String categoryID);
    Task<Discography> getDiscographyByArtistID(String artistID);
    Task<List<DiscographyForm>> getDiscographyHardMediaForm(String discographyID);
}
