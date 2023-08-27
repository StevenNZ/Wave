package com.example.wave.Repository;

import android.util.Log;

import com.example.wave.Dataproviders.OrderHistoryProvider;
import com.example.wave.Entities.CartOrder;
import com.example.wave.Entities.Order;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class OrderHistoryRepository implements OrderHistoryProvider {

    private static OrderHistoryRepository instance;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference orderHistoryCollection;

    private OrderHistoryRepository() {
    }

    public static OrderHistoryRepository getInstance() {
        if (instance == null) {
            instance = new OrderHistoryRepository();
        }
        return instance;
    }

    /**
     * check if user has a cart collection
     *
     * @param userID
     */
    private void checkUserOrderHistory(String userID) {
        orderHistoryCollection = db.collection(userID);
        orderHistoryCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot.isEmpty()) {
                    // Create collection
                    orderHistoryCollection = db.collection(userID);

                } else {
                    // Collection exists
                    DocumentReference documentReference = orderHistoryCollection.document("orderhistory");
                    documentReference.get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            DocumentSnapshot document = task1.getResult();
                            if (!document.exists()) {
                                // Create document
                                orderHistoryCollection.document("orderhistory");
                            } else {
                                // Document exists
                                Log.d("order history checker", "Document exists");
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

    @Override
    public List<List<CartOrder>> getOrderHistory(String userID) {
        return null;
    }

    @Override
    public void addOrder(String userID, List<CartOrder> checkoutCart) {
        checkUserOrderHistory(userID);
        orderHistoryCollection = db.collection(userID);
        String randomCollectionName = String.valueOf(System.currentTimeMillis());
        CollectionReference currentOrderHistory = orderHistoryCollection.document("orderhistory").collection(randomCollectionName);
        for (Order order : checkoutCart) {
            currentOrderHistory.add(order).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("order history", "order added");
                } else {
                    Log.d("order history", "order not added");
                }
            });
        }
    }

}
