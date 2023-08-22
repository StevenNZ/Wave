package com.example.wave.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wave.Domains.GetArtistUseCase;
import com.example.wave.Domains.GetCategoriesUseCase;
import com.example.wave.Domains.GetDiscographyUseCase;
import com.example.wave.Entities.Artist;
import com.example.wave.Entities.Discography;
import com.example.wave.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.PersistentCacheSettings;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FirebaseFirestoreSettings settings =
                new FirebaseFirestoreSettings.Builder(db.getFirestoreSettings())
                        // Use memory-only cache
                        .setLocalCacheSettings(MemoryCacheSettings.newBuilder().build())
                        // Use persistent disk cache (default)
                        .setLocalCacheSettings(PersistentCacheSettings.newBuilder()
                                .build())
                        .build();
        db.setFirestoreSettings(settings);

        GetDiscographyUseCase getDiscographyUseCase = new GetDiscographyUseCase();
        GetCategoriesUseCase getCategoriesUseCase = new GetCategoriesUseCase();
        GetArtistUseCase getArtistUseCase = new GetArtistUseCase();

        getCategoriesUseCase.getAllCategories();
        Task<List<Discography>> discographyList = getDiscographyUseCase.getAllDiscography();
        Task<List<Artist>> artistList = getArtistUseCase.getAllArtists();

        discographyList.addOnSuccessListener(discographies -> {
            artistList.addOnSuccessListener(artists -> {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            });
        });







    }
}
