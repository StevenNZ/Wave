package com.example.wave.Repository;

import android.util.Log;

import com.example.wave.Dataproviders.WishlistProvider;
import com.example.wave.Entities.Discography;
import com.example.wave.Entities.Order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;

public class WishlistRepository implements WishlistProvider {

    private static WishlistRepository instance;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference wishlistCollection; //set per user

    private WishlistRepository() {
    }

    public static WishlistRepository getInstance() {
        if (instance == null) {
            instance = new WishlistRepository();
        }
        return instance;
    }

    /**
     * check if user has a wishlist collection
     * @param userID
     */
    private void checkUserWishList(String userID) {
        wishlistCollection = db.collection(userID);
        wishlistCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot.isEmpty()) {
                    // Create collection
                    wishlistCollection = db.collection(userID);

                } else {
                    // Collection exists
                    DocumentReference documentReference = wishlistCollection.document("wishlist");
                    documentReference.get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            DocumentSnapshot document = task1.getResult();
                            if (!document.exists()) {
                                // Create document
                                wishlistCollection.document("wishlist");
                            } else {
                                // Document exists
                                Log.d("wishlist checker", "Document exists");
                                return;
                            }
                        } else {
                            // Handle errors
                        }
                    });

                }
            } else {
                // Handle errors
            }
        });
    }

    /**
     * get the wishlist of a user
     *
     * @param userID
     * @return a list of orders
     */
    @Override
    public Task<List<Order>> getWishlist(String userID) {
        checkUserWishList(userID);
        wishlistCollection = db.collection(userID);
        Task<QuerySnapshot> queryTask = wishlistCollection.document("wishlist").collection("orders").get();

        return queryTask.continueWith(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                List<DocumentSnapshot> documents = querySnapshot.getDocuments();
                List<Order> wishlistList = new ArrayList<>();
                for (DocumentSnapshot document : documents) {
                    wishlistList.add(document.toObject(Order.class));
                }
                return wishlistList;
            } else {
                Log.d("wishlist", "getWishlist: " + task.getException());
                return Collections.emptyList(); // Return an empty list in case of error
            }
        });
    }

    /**
     * check if an item is on the wishlist
     * @param userID
     * @param orderID
     * @return
     */
    @Override
    public Task<Boolean> checkItemOnWishlist(String userID, String orderID) {
        checkUserWishList(userID);
        wishlistCollection = db.collection(userID);
        Task<QuerySnapshot> queryTask = wishlistCollection.document("wishlist").collection("orders").get();

        return queryTask.continueWith(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                List<DocumentSnapshot> documents = querySnapshot.getDocuments();
                for (DocumentSnapshot document : documents) {
                    Order existingOrder = document.toObject(Order.class);
                    if (existingOrder != null && existingOrder.getOrderID().equals(orderID)) {
                        return true;
                    }
                }
                return false;
            }
            return null;
        });
    }


    /**
     * append an order item to the wishlist
     *
     * @param userID
     * @param wishlistOrder
     */
    @Override
    public void appendWishlist(String userID, Order wishlistOrder) {
        checkUserWishList(userID);
        wishlistCollection = db.collection(userID);

        Task<QuerySnapshot> queryTask = wishlistCollection.document("wishlist").collection("orders").get();

        queryTask.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                List<DocumentSnapshot> documents = querySnapshot.getDocuments();

                // Check if the wishlist already contains the order
                boolean orderExists = false;
                for (DocumentSnapshot document : documents) {
                    Order existingOrder = document.toObject(Order.class);
                    if (existingOrder != null && existingOrder.equals(wishlistOrder)) {
                        orderExists = true;
                        break;
                    }
                }

                if (!orderExists) {
                    // Wishlist does not contain the order, add it
                    wishlistCollection.document("wishlist").collection("orders").document(wishlistOrder.getOrderID()).set(wishlistOrder)
                            .addOnCompleteListener(addTask -> {
                                if (addTask.isSuccessful()) {
                                    Log.d("wishlist", "Order added to wishlist");

                                } else {
                                    Log.d("wishlist", "Error adding order to wishlist: " + addTask.getException());
                                }
                            });
                } else {
                    Log.d("wishlist", "Order already exists in the wishlist");
                }
            } else {
                Log.d("wishlist", "Error getting wishlist orders: " + task.getException());
            }
        });
    }


    /**
     * remove an item from the wishlist
     *
     * @param orderID
     * @return
     */
    @Override
    public Task<List<Order>> removeFromWishlistByOrderID(String userID, String orderID) {
        checkUserWishList(userID);
        wishlistCollection = db.collection(userID);

        // Get the reference to the order document that needs to be removed
        DocumentReference orderReference = wishlistCollection.document("wishlist")
                .collection("orders").document(orderID);

        // Delete the order document
        return orderReference.delete().continueWithTask(deleteTask -> {
            if (deleteTask.isSuccessful()) {
                // After deleting the order, fetch and return the updated wishlist
                return wishlistCollection.document("wishlist").collection("orders").get()
                        .continueWith(task -> {
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                List<DocumentSnapshot> documents = querySnapshot.getDocuments();
                                List<Order> wishlistList = new ArrayList<>();
                                for (DocumentSnapshot document : documents) {
                                    wishlistList.add(document.toObject(Order.class));
                                }
                                return wishlistList;
                            } else {
                                throw task.getException();
                            }
                        });
            } else {
                throw deleteTask.getException();
            }
        });
    }




}
