package com.example.wave.Repository;

import com.example.wave.Dataproviders.ArtistProvider;
import com.example.wave.Entities.Artist;
import org.junit.jupiter.api.Test;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ArtistRepository specifically for Integration Testing
 */
public class ArtistRepositoryTests {

    @Test
    public void testGetAllArtists() {
        ArtistProvider artistProvider = ArtistRepository.getInstance();

        // Act
        Task<List<Artist>> task = artistProvider.getAllArtists();
        task.addOnSuccessListener(new OnSuccessListener<List<Artist>>() {
            @Override
            public void onSuccess(List<Artist> artists) {
                // Assert
                assertNotNull(artists);
                assertTrue(artists.size() > 0);

                for (Artist artist : artists) {
                    assertNotNull(artist.getArtistID());
                    System.out.println(artist.getArtistID());
                    assertNotNull(artist.getArtistName());
                }
            }
        });
    }
}

