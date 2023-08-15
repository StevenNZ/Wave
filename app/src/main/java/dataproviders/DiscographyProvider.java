package dataproviders;

import java.util.List;

import entities.Discography;
import entities.DiscographyForm;

public interface DiscographyProvider {
    DiscographyRepository getInstance();
    List<Discography> getAllDiscography(String discographyID);
    List<Discography> getDiscographyByDiscographyID(String discographyID);
    List<Discography> getDiscographyBySearch(String searchString);
    List<Discography> getDiscographyByCategoryID(String categoryID);
    List<Discography> getDiscographyByArtistID(String artistID);
    List<Discography> getDiscographyByCategory(String categoryID);
    List<DiscographyForm> getDiscographyHardMediaForm(String discographyID);
}

