package com.example.wave.Repository;

import com.example.wave.Dataproviders.ArtistProvider;
import com.example.wave.Entities.Artist;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ArtistRepository implements ArtistProvider {
    private static ArtistRepository instance;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference artistsCollection = db.collection("artists");

    private ArtistRepository() {

    }

    /**
     * Singleton pattern
     * @return instance of ArtistRepository
     */
    public static ArtistRepository getInstance() {
        if (instance == null) {
            instance = new ArtistRepository();
        }
        return instance;
    }

    /**
     * Get all artists from the database
     * @return Task<List<Artist>> with all artists
     */
    @Override
    public Task<List<Artist>> getAllArtists() {
        return artistsCollection.get().continueWith(task -> {
            if (task.isSuccessful()) {
                List<Artist> artists = new ArrayList<>();
                QuerySnapshot querySnapshot = task.getResult();
                for (QueryDocumentSnapshot document : querySnapshot) {
                    Artist artist = document.toObject(Artist.class);
                    artists.add(artist);
                }
                return artists;
            } else {
                throw task.getException();
            }
        });
    }

    /**
     * Gets an Artist by ID
     * @return Task<Artist> with the artist
     */
    @Override
    public Task<Artist> getArtistByID(String artistID) {
        return artistsCollection.document(artistID).get().continueWith(task -> {
            if (task.isSuccessful()) {
                Artist artist = task.getResult().toObject(Artist.class);
                return artist;
            } else {
                throw task.getException();
            }
        });
    }

}
