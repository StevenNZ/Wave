package com.example.wave.Dataproviders;

import com.example.wave.Entities.Discography;
import com.example.wave.Entities.DiscographyForm;
import com.example.wave.Repository.DiscographyRepository;

import java.util.List;


public interface DiscographyProvider {
    DiscographyRepository getInstance();
    List<Discography> getAllDiscography(String discographyID);
    List<Discography> getDiscographyByDiscographyID(String discographyID);
    List<Discography> getDiscographyBySearch(String searchString);
    List<Discography> getDiscographyByCategoryID(String categoryID);
    List<Discography> getDiscographyByArtistID(String artistID);
    List<Discography> getDiscographyByCategory(String categoryID);
    List<DiscographyForm> getDiscographyHardMediaForm(String discographyID);
}

