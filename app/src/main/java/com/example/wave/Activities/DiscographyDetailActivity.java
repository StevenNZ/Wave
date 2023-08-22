package com.example.wave.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DiscographyDetailActivity extends AppCompatActivity {

    private final String cassettePrice = "$15";
    private final String cdPrice = "$20";
    private final String vinylPrice = "$40";

    private int position = 0;
    private int slide = 0;
    private ImageSlider imageSlider;
    private ImageButton currentImageButton;
    private DiscographyDetailViewModel model;
    private List<String> trackLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ConstraintLayout constraintLayout = findViewById(R.id.detailLayout);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        imageSlider = findViewById(R.id.imageSlider);
        ImageButton cassette = findViewById(R.id.cassetteBtn);
        ImageButton vinyl = findViewById(R.id.vinylBtn);
        ImageButton cd = findViewById(R.id.cdBtn);
        TextView albumTextView = findViewById(R.id.albumTitleText);
        TextView artistTextView = findViewById(R.id.artistText);
        TextView priceTextView = findViewById(R.id.priceText);
        LinearLayout tracklistLayout = findViewById(R.id.tracklistLayout);

        ArrayList<SlideModel> imageList = new ArrayList<>();
        currentImageButton = cassette;

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1750);
        animationDrawable.setExitFadeDuration(3500);
        animationDrawable.start();

        Intent intent = getIntent();
        String discographyId = intent.getStringExtra("DiscographyId");

        artistTextView.setText(intent.getStringExtra("ArtistName"));

        model = new DiscographyDetailViewModel();
        model.getDiscographyDetail(discographyId).addOnCompleteListener(new OnCompleteListener<Discography>() {
            @Override
            public void onComplete(@NonNull Task<Discography> task) {
                if (!task.isSuccessful()) {
                    Log.d(TAG, "onComplete: discography query not successful");
                } else {
                    Discography discographyResult = task.getResult();

                    albumTextView.setText(discographyResult.getReleaseName());

                    imageList.add(new SlideModel(discographyResult.getCassetteImageUrl(), "", ScaleTypes.CENTER_INSIDE));
                    imageList.add(new SlideModel(discographyResult.getVinylImageUrl(), "", ScaleTypes.CENTER_INSIDE));
                    imageList.add(new SlideModel(discographyResult.getCdImageUrl(), "", ScaleTypes.CENTER_INSIDE));
                    imageSlider.setImageList(imageList);

                    trackLists = discographyResult.getTracklist();
                    for (int i = 0; i < trackLists.size(); i++) {
                        // Create a new TextView for each item
                        TextView textView = new TextView(getBaseContext());
                        textView.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        ));
                        String text = i+1 + ". " + trackLists.get(i);
                        textView.setText(text);
                        textView.setTextAppearance(R.style.tracklistItem);
                        textView.setPadding(0, 0, 0, 20);

                        tracklistLayout.addView(textView);
                    }
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
                    priceTextView.setText(cassettePrice);
                } else if (position == 1) {
                    currentImageButton = vinyl;
                    priceTextView.setText(vinylPrice);
                } else {
                    currentImageButton = cd;
                    priceTextView.setText(cdPrice);
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