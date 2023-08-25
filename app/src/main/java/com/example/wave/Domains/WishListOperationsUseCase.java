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
                List<Order> orders = task.getResult();
                fetchWishListResults(orders, listener);

            } else {
                Exception exception = task.getException();
                // Handle the exception
            }
        });

    }

    private void fetchWishListResults(List<Order> orders, DiscographyResultsListener listener) {

        List<Popular> wishlist = new ArrayList<>();
        int totalOrders = orders.size();

        if(totalOrders == 0){
            listener.onDiscographyResultsReady(wishlist);
            return;
        }


        for (Order order : orders) {
            fetchDiscographyById(order.getDiscographyID(), (discography, artistName) -> {
                Popular res = new Popular(discography.getReleaseName(), artistName, discography.getImageURL(), discography.getDiscographyID());
                wishlist.add(res);

                if (wishlist.size() == totalOrders) {
                    listener.onDiscographyResultsReady(wishlist);
                }
            });
        }
    }

    private void fetchDiscographyById(String discogId, WishlistListener listener) {
        Task<Discography> discographyTask = DiscographyRepository.getInstance().getDiscographyByDiscographyID(discogId);

        discographyTask.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Discography discography = task.getResult();
                //returns a discography to fetchWishList results method via a listener

                fetchArtistName(discography.getArtistID(), artistName -> {
                    listener.onWishlistReady(discography, artistName);
                });

            }
        });
    }

    private void fetchArtistName(String artistID, ArtistNameListener artistNameListener) {

        Task<Artist> artist = ArtistRepository.getInstance().getArtistByID(artistID);

        artist.addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                artistNameListener.onArtistNameReady(artist.getResult().getArtistName());
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
