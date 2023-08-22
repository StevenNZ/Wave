package com.example.wave.Domains;

import com.example.wave.Dataproviders.DiscographyProvider;
import com.example.wave.Entities.Discography;
import com.example.wave.Repository.DiscographyRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class GetDiscographyUseCase implements DiscographyProvider {
    @Override
    public Task<List<Discography>> getAllDiscography() {
        return DiscographyRepository.getInstance().getAllDiscography();
    }

    @Override
    public Task<Discography> getDiscographyByDiscographyID(String discographyID) {
        return DiscographyRepository.getInstance().getDiscographyByDiscographyID(discographyID);
    }

    @Override
    public Task<List<Discography>> getDiscographyBySearch(String searchString) {
        return null;
    }

    @Override
    public Task<List<Discography>> getDiscographyByCategoryID(String categoryID) {
        return null;
    }

    @Override
    public Task<List<Discography>> getDiscographyByArtistID(String artistID) {
        return null;
    }

    @Override
    public void addViewToDiscography(String discographyID) {
        DiscographyRepository.getInstance().addViewToDiscography(discographyID);
    }

    @Override
    public Task<List<Discography>> getPopularDiscography() {
        return DiscographyRepository.getInstance().getPopularDiscography();
    }


}
