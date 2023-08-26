package com.example.wave.Activities;

import android.util.Log;

import com.example.wave.Adaptor.DiscographyAdapter;
import com.example.wave.Entities.Artist;
import com.example.wave.Entities.Discography;
import com.example.wave.Repository.ArtistRepository;
import com.example.wave.Repository.DiscographyRepository;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class SearchUseCase{

    private static String currentArtistName;

    public static void generateDiscographyResults(String query, String categoryId, TempResultListener listener) {

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
                listener.onTempReady(list);

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
