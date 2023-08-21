package com.example.wave.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.wave.Entities.Discography;
import com.example.wave.R;
import com.example.wave.ViewModel.DiscographyDetailViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class DiscographyDetailActivity extends AppCompatActivity {

    private int position = 0;
    private int slide = 0;
    private ImageSlider imageSlider;
    private ImageButton currentImageButton;
    private DiscographyDetailViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        imageSlider = findViewById(R.id.imageSlider);
        ImageButton cassette = findViewById(R.id.cassetteBtn);
        ImageButton vinyl = findViewById(R.id.vinylBtn);
        ImageButton cd = findViewById(R.id.cdBtn);
        TextView albumTextView = findViewById(R.id.albumTitleText);
        TextView artistTextView = findViewById(R.id.artistText);

        ArrayList<SlideModel> imageList = new ArrayList<>();
        currentImageButton = cassette;

        String id = "goodkidmaadcity";
        model = new DiscographyDetailViewModel();
        model.getDiscographyDetail(id).addOnCompleteListener(new OnCompleteListener<Discography>() {
            @Override
            public void onComplete(@NonNull Task<Discography> task) {
                if (!task.isSuccessful()) {
                    Log.d(TAG, "onComplete: discography query not successful");
                } else {
                    Discography discographyResult = task.getResult();

                    albumTextView.setText(discographyResult.getReleaseName());
                    artistTextView.setText(discographyResult.getArtistID());

                    imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fastroworld.png?alt=media&token=06728771-f194-4bf3-ab45-d207d8f18d73", "", ScaleTypes.CENTER_INSIDE));
                    imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fcollegedropout.png?alt=media&token=87b58d54-6688-475e-8f41-d65f6ee28742", "", ScaleTypes.CENTER_INSIDE));
                    imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fdamn.png?alt=media&token=21728145-9c54-48af-8b6c-433963e4b711", "", ScaleTypes.CENTER_INSIDE));
                    imageSlider.setImageList(imageList);
                }
            }
        });
        imageSlider.setSlideAnimation(AnimationTypes.DEPTH_SLIDE);
        imageSlider.setItemChangeListener(new ItemChangeListener() {
            @Override
            public void onItemChanged(int i) {
                position = i;

                if (slide == 2) {
                    slide--;
                } else if (slide == 1) {
                    slide--;
                    imageSlider.stopSliding();
                }

                currentImageButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white,null)));
                if (position == 0) {
                    currentImageButton = cassette;
                } else if (position == 1) {
                    currentImageButton = vinyl;
                } else {
                    currentImageButton = cd;
                }
                currentImageButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gold,null)));
            }
        });

        cassette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 1) {
                    startSlide(2);
                } else if (position == 2) {
                    startSlide(1);
                }
            }
        });

        vinyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 2) {
                    startSlide(2);
                } else if (position == 0) {
                    startSlide(1);
                }
            }
        });

        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    startSlide(2);
                } else if (position == 1) {
                    startSlide(1);
                }
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void startSlide(int slideNumber) {
        imageSlider.startSliding(10);
        slide = slideNumber;
    }
}