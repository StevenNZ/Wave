package com.example.wave.Domains;

import android.util.Log;

import com.example.wave.Activities.ArtistNameListener;
import com.example.wave.Activities.CategoryBreakdown;
import com.example.wave.Activities.CategoryResultsListener;
import com.example.wave.Activities.DiscographyResultsListener;
import com.example.wave.Activities.Popular;
import com.example.wave.Dataproviders.DiscographyProvider;
import com.example.wave.Entities.Artist;
import com.example.wave.Entities.Category;
import com.example.wave.Entities.Discography;
import com.example.wave.Repository.ArtistRepository;
import com.example.wave.Repository.CategoryRepository;
import com.example.wave.Repository.DiscographyRepository;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class GetPopularProductsUseCase implements DiscographyProvider {



    public void getPopularDiscography(DiscographyResultsListener listener){
        Task<List<Discography>> popularList = DiscographyRepository.getInstance().getPopularDiscography();

        popularList.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Discography> list = task.getResult();
                List<Popular> popularResults = new ArrayList<>();

                Log.d("SearchDebug", "from returned = " + list);

                for (Discography discography : list) {
                    String discogName = discography.getReleaseName();
                    String discogImage = discography.getImageURL();
                    String discogId = discography.getDiscographyID();
                    discographyArtistName(discography.getArtistID(), new ArtistNameListener() {
                        @Override
                        public void onArtistNameReady(String name) {
                            Popular res = new Popular(discogName, name, discogImage, discogId);
                            popularResults.add(res);

                            if (popularResults.size() == list.size()) {
                                // Call the listener with the populated list
                                listener.onDiscographyResultsReady(popularResults);
                            }
                        }
                    });
                }
            } else {
                Exception exception = task.getException();
                // Handle the exception
            }
        });

    };

    private static void discographyArtistName(String artistId, ArtistNameListener listener){


        Task<Artist> artistTask = ArtistRepository.getInstance().getArtistByID(artistId);

        artistTask.addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                listener.onArtistNameReady(artistTask.getResult().getArtistName());
            }
        });


    }

    @Override
    public Task<List<Discography>> getAllDiscography() {
        return null;
    }

    @Override
    public Task<Discography> getDiscographyByDiscographyID(String discographyID) {
        return null;
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

    }

    @Override
    public Task<List<Discography>> getPopularDiscography() {
        return null;
    }
}
