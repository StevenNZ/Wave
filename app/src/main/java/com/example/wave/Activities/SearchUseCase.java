package com.example.wave.Activities;

import android.util.Log;

import com.example.wave.Entities.Artist;
import com.example.wave.Entities.Discography;
import com.example.wave.Repository.ArtistRepository;
import com.example.wave.Repository.DiscographyRepository;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class SearchUseCase {

    private static String currentArtistName;

    public static void generateDiscographyResults(String query, String categoryId, DiscographyResultsListener listener) {

        Log.d("SearchDebug", "THIS THE QUERY WE LOOKING FOR DOG = " + query);
        Task<List<Discography>> discographyList;
        if(query.isEmpty()){
            discographyList = DiscographyRepository.getInstance().getDiscographyByCategoryID(categoryId);
        }else{
            discographyList = DiscographyRepository.getInstance().getDiscographyBySearch(query);
        }

        discographyList.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Discography> list = task.getResult();
                List<Popular> resultDiscographies = new ArrayList<>();
                if(list.isEmpty()){
                    listener.onDiscographyResultsReady(resultDiscographies);
                }


                Log.d("SearchDebug", "from returned = " + list);

                for (Discography discography : list) {
                    String discogName = discography.getReleaseName();
                    String discogImage = discography.getImageURL();
                    String discogId = discography.getDiscographyID();
                    discographyArtistName(discography.getArtistID(), new ArtistNameListener() {
                        @Override
                        public void onArtistNameReady(String name) {
                            Popular res = new Popular(discogName, name, discogImage, discogId);
                            resultDiscographies.add(res);

                            if (resultDiscographies.size() == list.size()) {
                                // Call the listener with the populated list
                                listener.onDiscographyResultsReady(resultDiscographies);
                            }
                        }
                    });
                }

                // Call the listener with the populated list
                //listener.onDiscographyResultsReady(resultDiscographies);
            } else {
                Exception exception = task.getException();
                // Handle the exception
            }
        });
    }


    private static void discographyArtistName(String artistId, ArtistNameListener listener){


        Task<Artist> artistTask = ArtistRepository.getInstance().getArtistByID(artistId);

        artistTask.addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                listener.onArtistNameReady(artistTask.getResult().getArtistName());
            }
        });


    }

}
