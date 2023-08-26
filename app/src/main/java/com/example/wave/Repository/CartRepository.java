package com.example.wave.Repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wave.Dataproviders.CartProvider;
import com.example.wave.Dataproviders.OrderHistoryProvider;
import com.example.wave.Entities.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartRepository implements CartProvider {

    private static CartRepository instance;

    private OrderHistoryRepository orderHistoryRepository = OrderHistoryRepository.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference cartCollection;

    private CartRepository() {
    }

    public static synchronized CartRepository getInstance() {
        if (instance == null) {
            instance = new CartRepository();
        }
        return instance;
    }

    /**
     * check if user has a cart collection
     *
     * @param userID
     */
    private void checkUserCart(String userID) {
        cartCollection = db.collection(userID);
        cartCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot.isEmpty()) {
                    // Create collection
                    cartCollection = db.collection(userID);

                } else {
                    // Collection exists
                    DocumentReference documentReference = cartCollection.document("cart");
                    documentReference.get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            DocumentSnapshot document = task1.getResult();
                            if (!document.exists()) {
                                // Create document
                                cartCollection.document("cart");
                            } else {
                                // Document exists
                                Log.d("cart checker", "Document exists");
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
     * Deletes a cart from the database based on a userID
     * THIS METHOD SHOULD NEVER BE CALLED WITHOUT
     * CREATING A NEW CART FOR THE USER AFTERWARDS
     * IN THE SAME TRANSACTION
     *
     * @param userID
     */
    private void deleteCart(String userID) {
        DocumentReference userDocument = cartCollection.document(userID);
        userDocument.delete();
    }

    /**
     * Gets a cart from the database based on a userID
     *
     * @param userID
     * @return Task<Order> with the cart
     */
    @Override
    public Task<List<Order>> getCart(String userID) {
        checkUserCart(userID);
        cartCollection = db.collection(userID);
        Task<QuerySnapshot> queryTask = cartCollection.document("cart").collection("orders").get();

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
                Log.d("cart", "getWishlist: " + task.getException());
                return Collections.emptyList(); // Return an empty list in case of error
            }
        });

    }

    /**
     * Adds a discographyFormID to the cart of a user
     */
    @Override
    public void addCartItems(String userID, Order cartOrder) {
        checkUserCart(userID);
        cartCollection = db.collection(userID);

        // Wishlist does not contain the order, add it
        cartCollection.document("cart").collection("orders").document(cartOrder.getOrderID()).set(cartOrder)
                .addOnCompleteListener(addTask -> {
                    if (addTask.isSuccessful()) {
                        Log.d("cart", "Order added to cart");

                    } else {
                        Log.d("cart", "Error adding order to cart: " + addTask.getException());
                    }
                });
    }

    /**
     * Removes a discographyFormID from the cart of a user
     */
    @Override
    public Task<List<Order>> removeFromCartByOrderID(String userID, String orderID) {
        checkUserCart(userID);
        cartCollection = db.collection(userID);

        // Get the reference to the order document that needs to be removed
        DocumentReference orderReference = cartCollection.document("cart")
                .collection("orders").document(orderID);

        // Delete the order document
        return orderReference.delete().continueWithTask(deleteTask -> {
            if (deleteTask.isSuccessful()) {
                // After deleting the order, fetch and return the updated wishlist
                return cartCollection.document("cart").collection("orders").get()
                        .continueWith(task -> {
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                List<DocumentSnapshot> documents = querySnapshot.getDocuments();
                                List<Order> cartList = new ArrayList<>();
                                for (DocumentSnapshot document : documents) {
                                    cartList.add(document.toObject(Order.class));
                                }
                                return cartList;
                            } else {
                                throw task.getException();
                            }
                        });
            } else {
                throw deleteTask.getException();
            }
        });
    }


    /**
     * checks out the cart of a user
     *
     * @param userID
     */
    @Override
    public void checkoutCart(String userID) {
        checkUserCart(userID);
        cartCollection = db.collection(userID);
        // Get the reference to the cart document

        this.getCart(userID).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Order> cart = task.getResult();
                orderHistoryRepository.addOrder(userID, cart);

                // Delete the cart document after adding it to the order history
                DocumentReference cartReference = cartCollection.document("cart");
                cartReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Cart successfully checked out!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error checking out cart", e);
                    }
                });

            } else {
                Log.d("checkout", "Error checking out cart getter: " + task.getException());
            }
        });


    }

    @Override
    public Task<Boolean> checkItemInCart(String userID, String orderID) {
        checkUserCart(userID);
        cartCollection = db.collection(userID);
        Task<QuerySnapshot> queryTask = cartCollection.document("cart").collection("orders").get();

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
}
