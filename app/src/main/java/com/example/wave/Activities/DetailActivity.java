package com.example.wave.Activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.wave.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private int position = 0;
    private int slide = 0;
    private ImageSlider imageSlider;
    private ImageButton currentImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        imageSlider = findViewById(R.id.imageSlider);
        ImageButton cassette = findViewById(R.id.cassetteBtn);
        ImageButton vinyl = findViewById(R.id.vinylBtn);
        ImageButton cd = findViewById(R.id.cdBtn);
        currentImageButton = cassette;

        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fastroworld.png?alt=media&token=06728771-f194-4bf3-ab45-d207d8f18d73", "", ScaleTypes.CENTER_INSIDE));
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fcollegedropout.png?alt=media&token=87b58d54-6688-475e-8f41-d65f6ee28742", "", ScaleTypes.CENTER_INSIDE));
        imageList.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/softeng306.appspot.com/o/albumHipHopImages%2Fdamn.png?alt=media&token=21728145-9c54-48af-8b6c-433963e4b711", "", ScaleTypes.CENTER_INSIDE));

        imageSlider.setImageList(imageList);
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