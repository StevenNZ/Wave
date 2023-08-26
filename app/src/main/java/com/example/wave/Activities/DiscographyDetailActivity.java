package com.example.wave.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.wave.Domains.AuthenticationUserUseCase;
import com.example.wave.Domains.GetCartUseCase;
import com.example.wave.Domains.GetDiscographyUseCase;
import com.example.wave.Domains.GetWishlistUseCase;
import com.example.wave.Entities.Discography;
import com.example.wave.Entities.Order;
import com.example.wave.R;
import com.example.wave.ViewModel.DiscographyDetailViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.like.LikeButton;
import com.like.OnLikeListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DiscographyDetailActivity extends AppCompatActivity {

    private final String cassettePrice = "$15";
    private final String cdPrice = "$20";
    private final String vinylPrice = "$40";

    private GetWishlistUseCase getWishlistUseCase = new GetWishlistUseCase();

    private GetCartUseCase getCartUseCase = new GetCartUseCase();

    private int position = 0;
    private int slide = 0;
    private boolean isSlide = true;
    private ImageSlider imageSlider;
    private ImageButton currentImageButton;
    private DiscographyDetailViewModel model;
    private List<String> trackLists;
    private int quantity = 1;

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
        LikeButton wishlistButton = findViewById(R.id.heartIcon);
        TextView albumTextView = findViewById(R.id.albumTitleText);
        TextView artistTextView = findViewById(R.id.artistText);
        TextView priceTextView = findViewById(R.id.priceText);
        LinearLayout tracklistLayout = findViewById(R.id.tracklistLayout);
        ImageButton decrementBtn = findViewById(R.id.decrementBtn);
        ImageButton incrementBtn = findViewById(R.id.incrementBtn);
        TextView quantityField = findViewById(R.id.quantityField);
        Button cartBtn = findViewById(R.id.cartBtn);

        ArrayList<SlideModel> imageList = new ArrayList<>();
        currentImageButton = cassette;



        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1750);
        animationDrawable.setExitFadeDuration(3500);
        animationDrawable.start();

        Intent intent = getIntent();
        String discographyId = intent.getStringExtra("DiscographyId");

        AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();

        if (authenticationUserUseCase.isLogin()) {
            String userId = authenticationUserUseCase.getUserID();
            getCartUseCase.checkItemInCart(userId, discographyId) //check if item is in cart
                    .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                        @Override
                        public void onComplete(@NonNull Task<Boolean> task) {
                            if (!task.isSuccessful()) {
                                Log.d(TAG, "onComplete: check item in cart query not successful");
                            } else {
                                Boolean result = task.getResult();
                                if (result) {

                                    cartBtn.setText("Added to Cart");
                                    cartBtn.setEnabled(false);
                                    wishlistButton.setEnabled(false);
                                }
                                else {
                                    getWishlistUseCase.checkItemOnWishlist(userId, discographyId) //check if item is on wishlist
                                            .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Boolean> task) {
                                                    if (!task.isSuccessful()) {
                                                        Log.d(TAG, "onComplete: check item on wishlist query not successful");
                                                    } else {
                                                        Boolean result = task.getResult();
                                                        if (result) {
                                                            wishlistButton.setLiked(true);
                                                        }
                                                    }
                                                }
                                            });
                                }
                            }
                        }
                    });
        }


        //add view to discography
        GetDiscographyUseCase getDiscographyUseCase = new GetDiscographyUseCase();
        getDiscographyUseCase.addViewToDiscography(discographyId);


        artistTextView.setText(intent.getStringExtra("ArtistName"));

        model = new ViewModelProvider(this).get(DiscographyDetailViewModel.class);
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
                    if (isSlide) {
                        priceTextView.setText(cassettePrice);
                    }
                } else if (position == 1) {
                    currentImageButton = vinyl;
                    if (isSlide) {
                        priceTextView.setText(vinylPrice);
                    }
                } else {
                    currentImageButton = cd;
                    if (isSlide) {
                        priceTextView.setText(cdPrice);
                    }
                }
                isSlide = true;
                currentImageButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gold,null)));
            }
        });

        cassette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 1) {
                    startSlide(2);
                    isSlide = false;
                } else if (position == 2) {
                    startSlide(1);
                }
            }
        });

        vinyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 2) {
                    isSlide = false;
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
                    isSlide = false;
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

        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                quantityField.setText(String.valueOf(quantity));
            }
        });

        decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    quantityField.setText(String.valueOf(quantity));
                }
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
                if (authenticationUserUseCase.isLogin()) {
                    String userID = authenticationUserUseCase.getUserID();
                    Order cartOrder = new Order(discographyId, "cart", userID, discographyId);
                    getCartUseCase.addCartItems(userID, cartOrder);
                    getWishlistUseCase.removeFromWishlistByOrderID(userID, discographyId);
                    wishlistButton.setLiked(false);
                    cartBtn.setText("Added to Cart");
                    cartBtn.setEnabled(false);

                } else {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        wishlistButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
                    if (authenticationUserUseCase.isLogin()) {
                        String userID = authenticationUserUseCase.getUserID();
                        Order wishlistOrder = new Order(discographyId, "wishlist", userID, discographyId);
                        getWishlistUseCase.appendWishlist(userID, wishlistOrder);
                    } else {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }

            @Override
            public void unLiked(LikeButton likeButton) {
                AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
                if (authenticationUserUseCase.isLogin()) {
                    String userID = authenticationUserUseCase.getUserID();
                    getWishlistUseCase.removeFromWishlistByOrderID(userID, discographyId);
                } else {
                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void startSlide(int slideNumber) {
        imageSlider.startSliding(10);
        slide = slideNumber;
    }
}