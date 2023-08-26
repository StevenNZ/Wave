package com.example.wave.Activities;

import com.example.wave.Entities.Discography;

import java.util.List;

public interface TempResultListener {
    void onTempReady(List<Discography> resultList);

}
