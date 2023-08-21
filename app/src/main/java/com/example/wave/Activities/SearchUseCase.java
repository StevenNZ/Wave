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

    public static void generateDiscographyResults(String query, String categoryId, DiscographyResultsListener listener) {

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

                Log.d("SearchDebug", "from returned = " + list);

                for (Discography discography : list) {
                    String discogName = discography.getReleaseName();
                    String artistName = discography.getArtistID();
                    String discogImage = discography.getImageURL();
                    String discogId = discography.getDiscographyID();

                    Popular res = new Popular(discogName, artistName, discogImage, discogId);
                    resultDiscographies.add(res);
                }

                // Call the listener with the populated list
                listener.onDiscographyResultsReady(resultDiscographies);
            } else {
                Exception exception = task.getException();
                // Handle the exception
            }
        });
    }
}
