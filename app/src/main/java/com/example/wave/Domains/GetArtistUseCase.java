package com.example.wave.Domains;

import com.example.wave.Dataproviders.ArtistProvider;
import com.example.wave.Entities.Artist;
import com.example.wave.Repository.ArtistRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class GetArtistUseCase implements ArtistProvider {
    @Override
    public Task<List<Artist>> getAllArtists() {
        return ArtistRepository.getInstance().getAllArtists();
    }

    @Override
    public Task<Artist> getArtistByID(String artistID) {
        return ArtistRepository.getInstance().getArtistByID(artistID);
    }
}
