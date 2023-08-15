package dataproviders;

import java.util.List;

import entities.Artist;

public interface ArtistProvider {
    ArtistRepository getInstance();
    List<Artist> getAllArtists();
    List<Artist> getArtistsByID(String categoryID);
}
