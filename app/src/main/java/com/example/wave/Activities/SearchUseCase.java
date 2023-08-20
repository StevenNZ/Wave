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

        // Replace OnDiscographyResultsListener with an appropriate interface or callback structure
        // that will receive the results once the task is complete.

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

//    public static List<Popular> getSearchedDiscography(String query){
//
//        //what this does is get all the discographies we get but only returns the stuff we need for the image
//        //This is how he did it so I think it's the best, but if not we can adapt it.
//        //Can pass ID's through somehow if need be.
//        //List<Artist> discographies = generateDiscographyResults(query);
//        List<Popular> resultDiscographies = new ArrayList<>();
//
//        Log.d("SearchDebug", "from returned = " + discographies);
//
//        for (Artist discography: discographies){
//            String discogName = discography.getArtistName();
//            String artistName = discography.getArtistID();
//            String discogImage = discography.getArtistID();
//            Log.d("SearchDebug", "discog Image = " + discogImage);
//
//
//            Popular res = new Popular(discogName, artistName, discogImage);
//            resultDiscographies.add(res);
//        }
//
//        return resultDiscographies;
//
//    }

//}
