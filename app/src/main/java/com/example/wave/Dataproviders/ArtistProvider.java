package com.example.wave.Dataproviders;

import com.example.wave.Entities.Artist;

import java.util.List;



public interface ArtistProvider {
    ArtistRepository getInstance();
    List<Artist> getAllArtists();
    List<Artist> getArtistsByID(String categoryID);
}
