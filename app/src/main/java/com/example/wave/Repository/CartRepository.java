package com.example.wave.Repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wave.Dataproviders.CartProvider;
import com.example.wave.Entities.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CartRepository implements CartProvider {

    private static CartRepository instance;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final CollectionReference cartsCollection = db.collection("Cart");

    private CartRepository() {
    }

    public static synchronized CartRepository getInstance() {
        if (instance == null) {
            instance = new CartRepository();
        }
        return instance;
    }

    /**
     * Creates a cart for a user in the database
     *
     * @param userID
     */
    @Override
    public void createCart(String userID) {
        String orderID = userID + "Cart";
        List<String> discographyFormIDList = null;
        String orderDate = null;
        Order cart = new Order(orderID, userID, discographyFormIDList, orderDate);
        cartsCollection.document(userID)
                .set(cart)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Cart successfully created!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    /**
     * Deletes a cart from the database based on a userID
     * <p>
     * THIS METHOD SHOULD NEVER BE CALLED WITHOUT
     * CREATING A NEW CART FOR THE USER AFTERWARDS
     * IN THE SAME TRANSACTION
     *
     * @param userID
     */
    private void deleteCart(String userID) {
        DocumentReference userDocument = cartsCollection.document(userID);
        userDocument.delete();
    }

    /**
     * Gets a cart from the database based on a userID
     *
     * @param userID
     * @return Task<Order> with the cart
     */
    @Override
    public Task<Order> getCart(String userID) {
        DocumentReference userDocument = cartsCollection.document(userID);
        return userDocument.get().continueWith(task -> {
            if (task.isSuccessful()) {
                Order order = task.getResult().toObject(Order.class);
                return order;
            } else {
                throw task.getException();
            }
        });

    }

    /**
     * Adds a discographyFormID to the cart of a user
     *
     * @param userID
     * @param discographyFormID
     */
    @Override
    public void addCartItems(String userID, String discographyFormID) {
        DocumentReference userDocument = cartsCollection.document(userID);
        userDocument.update("discographyFormIDList", FieldValue.arrayUnion(discographyFormID));
    }

    /**
     * Removes a discographyFormID from the cart of a user
     *
     * @param userID
     * @param discographyFormID
     */
    @Override
    public void removeCartItems(String userID, String discographyFormID) {
        DocumentReference userDocument = cartsCollection.document(userID);
        userDocument.update("discographyFormIDList", FieldValue.arrayRemove(discographyFormID));
    }


    /**
     * checks out the cart of a user
     *
     * @param userID
     */
    @Override
    public void checkoutCart(String userID) {
        //TODO: implement checkoutCart
        //need implementation of orderHistory first
    }
}
