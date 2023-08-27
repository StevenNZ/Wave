package com.example.wave.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.wave.Adaptor.ImageSliderAdapter;
import com.example.wave.Domains.AuthenticationUserUseCase;
import com.example.wave.Domains.GetCartUseCase;
import com.example.wave.Domains.GetDiscographyUseCase;
import com.example.wave.Domains.GetWishlistUseCase;
import com.example.wave.Entities.CartOrder;
import com.example.wave.Entities.Discography;
import com.example.wave.Entities.WishlistOrder;
import com.example.wave.R;
import com.example.wave.ViewModel.DiscographyDetailViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.like.LikeButton;
import com.like.OnLikeListener;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator3;

public class DiscographyDetailActivity extends AppCompatActivity {

    private String currentPrice = "0";
    private String currentFormat = "cassette";
    private GetWishlistUseCase getWishlistUseCase = new GetWishlistUseCase();
    private GetCartUseCase getCartUseCase = new GetCartUseCase();
    private ViewPager2 viewPager2;
    private ArrayList<String> viewPagerItemArray;
    private ImageButton currentImageButton;
    private DiscographyDetailViewModel model;
    private List<String> trackLists;
    private int quantity = 1;
    private boolean inCart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        RelativeLayout imageSliderLayout = findViewById(R.id.imageSliderLayout);
        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
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
        CircleIndicator3 indicator = findViewById(R.id.indicator);

        currentImageButton = cassette;
        viewPager2 = findViewById(R.id.imageSlider);

        // start image slider gradient background animation
        AnimationDrawable animationDrawable = (AnimationDrawable) imageSliderLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1250);
        animationDrawable.setExitFadeDuration(2500);
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
                                inCart = result;
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

        // get discography details from discography repository to update relevant detail information
        model.getDiscographyDetail(discographyId).addOnCompleteListener(new OnCompleteListener<Discography>() {
            @Override
            public void onComplete(@NonNull Task<Discography> task) {
                if (!task.isSuccessful()) {
                    Log.d(TAG, "onComplete: discography query not successful");
                } else {
                    Discography discographyResult = task.getResult();

                    // update tool bar and album textview with release name
                    String releaseName = discographyResult.getReleaseName();
                    toolbar.setTitle(releaseName);
                    albumTextView.setText(releaseName);

                    // add physical media images to viewpager array and set adapter
                    viewPagerItemArray = new ArrayList<>();
                    viewPagerItemArray.add(discographyResult.getCassetteImageUrl());
                    viewPagerItemArray.add(discographyResult.getVinylImageUrl());
                    viewPagerItemArray.add(discographyResult.getCdImageUrl());

                    ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(getBaseContext(), viewPagerItemArray, viewPager2);
                    viewPager2.setAdapter(imageSliderAdapter);
                    indicator.setViewPager(viewPager2);

                    // displays tracklist
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

                    // whenever viewpager scrolls to new page, update price and button colours
                    viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            currentImageButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white,null)));
                            String currentDiscographyPrice = discographyResult.getPrice();

                            Log.d(TAG, "currentDiscographyPrice: " + currentDiscographyPrice);

                            if (position == 0) {
                                currentImageButton = cassette;

                                String cassettePrice = String.valueOf((int) Math.ceil(Integer.parseInt(currentDiscographyPrice) * 1.5));

                                priceTextView.setText("$" + cassettePrice);
                                currentPrice = cassettePrice;
                                currentFormat = "cassette";
                            } else if (position == 1) {
                                currentImageButton = vinyl;

                                String vinylPrice = String.valueOf((int) Math.ceil(Integer.parseInt(currentDiscographyPrice) * 2));
                                priceTextView.setText("$" + vinylPrice);
                                currentPrice = vinylPrice;
                                currentFormat = "vinyl";

                            } else {
                                currentImageButton = cd;
                                String cdPrice = String.valueOf((int) Math.ceil(Integer.parseInt(currentDiscographyPrice) * 1));
                                priceTextView.setText("$" + cdPrice);
                                currentPrice = cdPrice;
                                currentFormat = "cd";

                            }
                            currentImageButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gold,null)));
                        }
                    });
                }
            }
        });




        cassette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(0, true);
            }
        });

        vinyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(1, true);
            }
        });

        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(2, true);
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
                if (inCart){
                    cartBtn.setText("Update Cart");
                    cartBtn.setEnabled(true);
                }
            }
        });

        decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    quantityField.setText(String.valueOf(quantity));
                    if (inCart){
                        cartBtn.setText("Update Cart");
                        cartBtn.setEnabled(true);
                    }
                }
                else if (quantity == 1 && inCart){
                        quantity--;
                        quantityField.setText(String.valueOf(quantity));
                        cartBtn.setText("Remove from Cart");
                        cartBtn.setEnabled(true);
                    }
                }

        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cartBtn.getText().equals("Add To Cart")) {
                    AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
                    if (authenticationUserUseCase.isLogin()) {
                        String userID = authenticationUserUseCase.getUserID();
                        inCart = true;
                        model.getDiscographyDetail(discographyId).addOnCompleteListener(new OnCompleteListener<Discography>() {
                            @Override
                            public void onComplete(@NonNull Task<Discography> task) {
                                if (!task.isSuccessful()) {
                                    Log.d(TAG, "onComplete: discography query not successful");
                                } else {
                                    Discography discography = task.getResult();
                                    CartOrder cartOrder = new CartOrder(discographyId, "cart", userID, discographyId, discography.getReleaseName(), intent.getStringExtra("ArtistName"), discography.getImageURL(), currentFormat, String.valueOf(quantity), currentPrice);
                                    getCartUseCase.addCartItems(userID, cartOrder);
                                    cartBtn.setText("Added to Cart");
                                    cartBtn.setEnabled(false);
                                    wishlistButton.setEnabled(false);
                                }
                            }
                        });

                    } else {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                } else if (cartBtn.getText().equals("Update Cart")) {
                    AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
                    if (authenticationUserUseCase.isLogin()) {
                        String userID = authenticationUserUseCase.getUserID();
                        getCartUseCase.updateQuantityByOrderID(userID, discographyId, String.valueOf(quantity));
                        cartBtn.setText("Updated Cart");
                        cartBtn.setEnabled(false);
                    } else {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                } else if (cartBtn.getText().equals("Remove from Cart")) {
                    AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
                    if (authenticationUserUseCase.isLogin()) {
                        String userID = authenticationUserUseCase.getUserID();
                        getCartUseCase.removeFromCartByOrderID(userID, discographyId);
                        cartBtn.setText("Add To Cart");
                        cartBtn.setEnabled(true);
                        wishlistButton.setEnabled(true);
                        quantity++;
                        quantityField.setText(String.valueOf(quantity));
                        inCart = false;
                    } else {
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });

        wishlistButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                AuthenticationUserUseCase authenticationUserUseCase = new AuthenticationUserUseCase();
                    if (authenticationUserUseCase.isLogin()) {
                        String userID = authenticationUserUseCase.getUserID();
                       model.getDiscographyDetail(discographyId).addOnCompleteListener(new OnCompleteListener<Discography>() {
                            @Override
                            public void onComplete(@NonNull Task<Discography> task) {
                                if (!task.isSuccessful()) {
                                    Log.d(TAG, "onComplete: discography query not successful");
                                } else {
                                    Discography discographyResult = task.getResult();
                                    WishlistOrder wishlistOrder = new WishlistOrder(discographyId, "wishlist", userID, discographyId, discographyResult.getReleaseName(), intent.getStringExtra("ArtistName"), discographyResult.getImageURL());
                                    getWishlistUseCase.appendWishlist(userID, wishlistOrder);
                                }
                            }
                        });
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
}