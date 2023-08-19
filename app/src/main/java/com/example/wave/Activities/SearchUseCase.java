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

    public static List<Artist> generateDiscographyResults(String query){

        //we make an arrayList of discography objetcs

        // call getDiscographyBySearch(searchString) which returns a List<Discography>
        // in the Discography Repository part of the UML


        //for testing purposes

        List<Artist> results = new ArrayList<>();

        Task<List<Artist>> artistList = ArtistRepository.getInstance().getAllArtists();

        //Task<List<Discography>> resultsList = DiscographyRepository.getInstance().getDiscographyBySearch(query);

        Log.d("SearchDebug", "from firestore = " + artistList);

        artistList.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Artist> discographyList = task.getResult();

                Log.d("SearchDebug", "on successful = " + discographyList);

                for (Artist artist : discographyList) {
                    // Convert and add to the final results list
                    results.add(artist);
                }

                // Now the 'results' list contains all Discography objects from 'resultsList'
            } else {
                Exception exception = task.getException();
                // Handle the exception
            }
        });

//        results.add(new Discography("1", "Travis Scott", "astroworld", "astroworld_image"));
//        results.add(new Discography("2", "Taylor Swift", "pop", "pop_image"));
//        results.add(new Discography("3", "Kpop", "Kpop", "kpop_image"));
//        results.add(new Discography("4", "Kanye West", "Graduation", "graduation_image"));
//        results.add(new Discography("5", "Travis Scott", "astroworld", "rodeo_image")); results.add(new Discography("1", "Travis Scott", "astroworld", "astroworld_image"));
//        results.add(new Discography("2", "Taylor Swift", "pop", "pop_image"));
//        results.add(new Discography("3", "Kpop", "Kpop", "kpop_image"));
//        results.add(new Discography("4", "Kanye West", "Graduation", "graduation_image"));
//        results.add(new Discography("5", "Travis Scott", "astroworld", "rodeo_image"));

        return results;

    }

    public static List<Popular> getSearchedDiscography(String query){

        //what this does is get all the discographies we get but only returns the stuff we need for the image
        //This is how he did it so I think it's the best, but if not we can adapt it.
        //Can pass ID's through somehow if need be.
        List<Artist> discographies = generateDiscographyResults(query);
        List<Popular> resultDiscographies = new ArrayList<>();

        Log.d("SearchDebug", "from returned = " + discographies);

        for (Artist discography: discographies){
            String discogName = discography.getArtistName();
            String artistName = discography.getArtistID();
            String discogImage = discography.getArtistID();
            Log.d("SearchDebug", "discog Image = " + discogImage);


            Popular res = new Popular(discogName, artistName, discogImage);
            resultDiscographies.add(res);
        }

        return resultDiscographies;

    }

}
