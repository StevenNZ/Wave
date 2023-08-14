package com.example.initial;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PopularDataProvider {



    public static Map<String, String> generatePopular(){

        Map<String, String> popular = new LinkedHashMap<String, String>();

        popular.put("astroworld", "Travis Scott");
        popular.put("graduation", "Kanye West");
        popular.put("kpop", "Blankpink");
        popular.put("rodeo", "Playboi Carti");
        return popular;
    }

    public static List<Popular> getPopular(){

        List<Popular> popularAlbums = new ArrayList<Popular>();
        Map<String, String> popularList = generatePopular();

        for (String key: popularList.keySet()){
            String albumName = key;
            String artistName = popularList.get(key);
            String albumCover = key + "_image";

            Popular popular = new Popular(albumName, artistName, albumCover);
            popularAlbums.add(popular);
        }

        return popularAlbums;

    }
}
