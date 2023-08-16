package com.example.wave.Activities;

import java.util.ArrayList;
import java.util.List;

public class SearchUseCase {

    public static List<Discography> generateDiscographyResults(){

        //we make an arrayList of discography objetcs

        // call getDiscographyBySearch(searchString) which returns a List<Discography>
        // in the Discography Repository part of the UML


        //for testing purposes
        List<Discography> results = new ArrayList<Discography>();

        results.add(new Discography("1", "Travis Scott", "astroworld", "astroworld_image"));
        results.add(new Discography("2", "Taylor Swift", "pop", "pop_image"));
        results.add(new Discography("3", "Kpop", "Kpop", "kpop_image"));
        results.add(new Discography("4", "Kanye West", "Graduation", "graduation_image"));
        results.add(new Discography("5", "Travis Scott", "astroworld", "rodeo_image")); results.add(new Discography("1", "Travis Scott", "astroworld", "astroworld_image"));
        results.add(new Discography("2", "Taylor Swift", "pop", "pop_image"));
        results.add(new Discography("3", "Kpop", "Kpop", "kpop_image"));
        results.add(new Discography("4", "Kanye West", "Graduation", "graduation_image"));
        results.add(new Discography("5", "Travis Scott", "astroworld", "rodeo_image"));

        return results;

    }

    public static List<Popular> getSearchedDiscography(){

        //what this does is get all the discographies we get but only returns the stuff we need for the image
        //This is how he did it so I think it's the best, but if not we can adapt it.
        //Can pass ID's through somehow if need be.
        List<Discography> discographies = generateDiscographyResults();
        List<Popular> resultDiscographies = new ArrayList<>();

        for (Discography discography: discographies){
            String discogName = discography.getReleaseName();
            String artistName = discography.getArtistID();
            String discogImage = discography.getReleaseArtworkURI();

            Popular res = new Popular(discogName, artistName, discogImage);
            resultDiscographies.add(res);
        }

        return resultDiscographies;

    }

}
