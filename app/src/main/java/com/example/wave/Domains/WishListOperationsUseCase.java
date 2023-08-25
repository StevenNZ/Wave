package com.example.wave.Domains;

import android.util.Log;

import com.example.wave.Activities.ArtistNameListener;
import com.example.wave.Activities.DiscographyResultsListener;
import com.example.wave.Activities.Popular;
import com.example.wave.Activities.WishlistListener;
import com.example.wave.Dataproviders.WishlistProvider;
import com.example.wave.Entities.Artist;
import com.example.wave.Entities.Discography;
import com.example.wave.Entities.Order;
import com.example.wave.Repository.ArtistRepository;
import com.example.wave.Repository.DiscographyRepository;
import com.example.wave.Repository.WishlistRepository;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class WishListOperationsUseCase implements WishlistProvider {

    private final WishlistRepository wishlistRepository = WishlistRepository.getInstance();

    public void getUserWishlist(String userId, DiscographyResultsListener listener){
        Task<List<Order>> wishList = wishlistRepository.getWishlist(userId);

        wishList.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Order> list = task.getResult();
                List<Popular> popularResults = new ArrayList<>();

                Log.d("SearchDebug", "from returned = " + list);

                for (Order order : list) {
                    discographyId(order.getDiscographyID(), new WishlistListener() {
                        @Override
                        public void onWishlistReady(Discography discography) {
                            Popular res = new Popular(discography.getReleaseName(), discography.getArtistID(), discography.getCassetteImageUrl(), discography.getDiscographyID());
                            popularResults.add(res);

                            if (popularResults.size() == list.size()) {
                                // Call the listener with the populated list
                                listener.onDiscographyResultsReady(popularResults);
                            }
                        }
                    });
                }
            } else {
                Exception exception = task.getException();
                // Handle the exception
            }
        });

    };

    private static void discographyId(String discogId, WishlistListener listener){


        Task<Discography> discographyTask = DiscographyRepository.getInstance().getDiscographyByDiscographyID(discogId);

        discographyTask.addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                listener.onWishlistReady(discographyTask.getResult());
            }
        });


    }


    @Override
    public Task<List<Order>> getWishlist(String userID) {
        return null;
    }

    @Override
    public Task<Boolean> checkItemOnWishlist(String userID, String orderID) {
        return null;
    }

    @Override
    public void appendWishlist(String userID, Order wishlistOrder) {

    }

    @Override
    public Task<List<Order>> removeFromWishlistByOrderID(String userID, String orderID) {
        return null;
    }
}
