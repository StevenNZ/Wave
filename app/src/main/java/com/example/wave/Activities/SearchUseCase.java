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

    public static void generateDiscographyResults(String query, DiscographyResultsListener listener) {


        Task<List<Artist>> artistList = ArtistRepository.getInstance().getAllArtists();

        artistList.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Artist> discographyList = task.getResult();
                List<Popular> resultDiscographies = new ArrayList<>();

                Log.d("SearchDebug", "from returned = " + discographyList);

                for (Artist artist : discographyList) {
                    String discogName = artist.getArtistName();
                    String artistName = artist.getArtistID();
                    String discogImage = artist.getArtistID();

                    Popular res = new Popular(discogName, artistName, discogImage);
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
