package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Activities.Popular;

import java.util.List;

public class WishlistViewModel extends ViewModel{

    private MutableLiveData<List<Popular>> wishlistLiveData = new MutableLiveData<>();

    public WishlistViewModel() {

    }


    public LiveData<List<Popular>> getWishlistLiveData(){
        if (wishlistLiveData.getValue() == null) {
            wishlistLiveData.setValue(null);
        }
        return wishlistLiveData;
    }

    public void updateWishlist(List<Popular> wishlist) {
        wishlistLiveData.setValue(wishlist);
    }


}
