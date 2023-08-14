package com.example.initial;

public class Popular {

    private String albumImage, albumName, albumArtist;

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public Popular(String albumName, String albumArtist, String albumImage){
        this.albumArtist = albumArtist;
        this.albumName = albumName;
        this.albumImage = albumImage;

    }
}
