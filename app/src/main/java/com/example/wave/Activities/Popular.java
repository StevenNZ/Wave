package com.example.wave.Activities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Popular {

    private String albumImage, albumName, albumArtist, discographyId;

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public String getDiscographyId() {
        return discographyId;
    }

    public Popular(String albumName, String albumArtist, String albumImage, String discographyId){
        this.albumArtist = albumArtist;
        this.albumName = albumName;
        this.albumImage = albumImage;
        this.discographyId = discographyId;

    }

}
