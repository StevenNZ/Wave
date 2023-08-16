package com.example.wave.Dataproviders;

import com.example.wave.Entities.Artist;
import com.example.wave.Repository.ArtistRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;



public interface ArtistProvider {
    static ArtistRepository getInstance() {
        return null;
    }

    Task<List<Artist>> getAllArtists();
    Task<List<Artist>> getArtistsByID(String categoryID);
}
