package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Entities.Order;

import java.util.List;

public class WishlistViewModel extends ViewModel{

    private MutableLiveData<List<Order>> wishlistLiveData = new MutableLiveData<>();

    public WishlistViewModel() {

    }


    public LiveData<List<Order>> getWishlistLiveData(){
        return wishlistLiveData;
    }

    public void updateWishlist(List<Order> wishlist) {
        wishlistLiveData.setValue(wishlist);
    }


}
