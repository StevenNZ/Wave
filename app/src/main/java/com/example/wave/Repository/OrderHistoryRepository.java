package com.example.wave.Repository;

import android.util.Log;

import com.example.wave.Dataproviders.OrderHistoryProvider;
import com.example.wave.Entities.CartOrder;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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
    public Task<List<List<CartOrder>>> getOrderHistory(String userID) {
        checkUserOrderHistory(userID);
        Task<QuerySnapshot> queryTask  = db.collection(userID).get();
        return queryTask.continueWith(task -> {
            List<List<CartOrder>> orderHistory = new ArrayList<>();
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                    if (documentSnapshot.getId().equals("orderhistory")) {
                        CollectionReference orderHistoryCollection = documentSnapshot.getReference().getParent();
                        Task<QuerySnapshot> orderHistoryQueryTask = orderHistoryCollection.get();
                        orderHistoryQueryTask.addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                QuerySnapshot orderHistoryQuerySnapshot = task1.getResult();
                                for (DocumentSnapshot orderHistoryDocumentSnapshot : orderHistoryQuerySnapshot.getDocuments()) {
                                    List<CartOrder> cartOrderList = new ArrayList<>();
                                    CollectionReference orderCollection = orderHistoryDocumentSnapshot.getReference().getParent();
                                    Task<QuerySnapshot> orderQueryTask = orderCollection.get();
                                    orderQueryTask.addOnCompleteListener(task2 -> {
                                        if (task2.isSuccessful()) {
                                            QuerySnapshot orderQuerySnapshot = task2.getResult();
                                            for (DocumentSnapshot orderDocumentSnapshot : orderQuerySnapshot.getDocuments()) {
                                                CartOrder cartOrder = orderDocumentSnapshot.toObject(CartOrder.class);
                                                cartOrderList.add(cartOrder);
                                            }
                                            orderHistory.add(cartOrderList);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
            return orderHistory;
        });
    }

    @Override
    public void addOrder(String userID, List<CartOrder> checkoutCart) {
        checkUserOrderHistory(userID);
        orderHistoryCollection = db.collection(userID);
        String randomCollectionName = String.valueOf(System.currentTimeMillis());
        CollectionReference currentOrderHistory = orderHistoryCollection.document("orderhistory").collection(randomCollectionName);
        for (CartOrder cartOrder : checkoutCart) {
            currentOrderHistory.document(cartOrder.getOrderID()).set(cartOrder).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("order history", "order added");
                } else {
                    Log.d("order history", "order not added");
                }
            });
        }
    }

}
