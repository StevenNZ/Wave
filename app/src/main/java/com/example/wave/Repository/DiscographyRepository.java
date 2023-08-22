package com.example.wave.Repository;

import android.util.Log;

import com.example.wave.Dataproviders.DiscographyProvider;
import com.example.wave.Entities.Discography;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DiscographyRepository implements DiscographyProvider {
    private static DiscographyRepository instance;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference discographyCollection = db.collection("Discography");

    private final CollectionReference discographyFormCollection = db.collection("DiscographyForm");
    private DiscographyRepository() {
    }

    public static synchronized DiscographyRepository getInstance() {
        if (instance == null) {
            instance = new DiscographyRepository();
        }
        return instance;
    }

    /**
     * Get all discography from the database
     *
     * @param discographyID
     * @return Task<List<Discography>> with all discography
     */
    @Override
    public Task<List<Discography>> getAllDiscography(String discographyID) {
        Task<QuerySnapshot> queryTask = discographyCollection.get();

        return queryTask.continueWith(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                List<DocumentSnapshot> documents = querySnapshot.getDocuments();
                List<Discography> discographyList = new ArrayList<>();

                for (DocumentSnapshot document : documents) {
                    Discography discography = document.toObject(Discography.class);
                    discographyList.add(discography);
                }

                return discographyList;
            } else {
                throw new ExecutionException(task.getException());
            }
        });

    }

    /**
     * Gets an Discography by a discographyID
     *
     * @param discographyID
     * @return Task<Discography> with the discography
     */
    @Override
    public Task<Discography> getDiscographyByDiscographyID(String discographyID) {
        Task<DocumentSnapshot> documentTask = discographyCollection.document(discographyID).get();

        return documentTask.continueWith(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot.exists()) {
                    Discography discography = documentSnapshot.toObject(Discography.class);
                    return discography;
                } else {
                    Log.d("getDiscographyByDiscographyID", "No matching documents with the given discographyID");
                    return null; // Document with the given ID does not exist
                }
            } else {
                throw task.getException();
            }
        });
    }

    /**
     * Get a list of discography by a search string
     * THIS IS A LOCAL FUZZY SEARCH
     * SPEED IS NOT GUARANTEED
     *
     * @param searchString
     * @return Task<List<Discography>> with the discography
     */
    @Override
    public Task<List<Discography>> getDiscographyBySearch(String searchString) {
        TaskCompletionSource<List<Discography>> taskCompletionSource = new TaskCompletionSource<>();

        Query query = discographyCollection;

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Discography> matchingDiscography = new ArrayList<>();
                for (DocumentSnapshot documentSnapshot : task.getResult()) {
                    Discography discography = documentSnapshot.toObject(Discography.class);

                    // Perform fuzzy search logic (e.g., check if productName contains searchTerm)
                    if (discography != null && discography.getReleaseName().toLowerCase().contains(searchString.toLowerCase())) {
                        matchingDiscography.add(discography);
                    }
                }
                taskCompletionSource.setResult(matchingDiscography);
            } else {
                Log.d("getDiscographyBySearch", "Error getting documents: " + task.getException());
                taskCompletionSource.setException(task.getException());
            }
        });

        return taskCompletionSource.getTask();
        

    }

    /**
     * Get a list of discography by a categoryID
     *
     * @param categoryID
     * @return Task<List<Discography>> with the discography
     */
    @Override
    public Task<List<Discography>> getDiscographyByCategoryID(String categoryID) {
        TaskCompletionSource<List<Discography>> taskCompletionSource = new TaskCompletionSource<>();

        Query query = discographyCollection.whereEqualTo("categoryID", categoryID);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    // The query returned matching documents

                    List<Discography> currentDiscographyList = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                        Discography discography = documentSnapshot.toObject(Discography.class);
                        currentDiscographyList.add(discography);

                    }
                    taskCompletionSource.setResult(currentDiscographyList);
                } else {
                    Log.d("getDiscographyByCategoryID", "No matching documents");
                    taskCompletionSource.setResult(null);
                }
            } else {
                Log.d("getDiscographyByCategoryID", "Error getting documents: " + task.getException());
                taskCompletionSource.setException(task.getException());
            }
        });

        return taskCompletionSource.getTask();
    }

    /**
     * Get a list of discography by a artistID
     *
     * @param artistID
     * @return Task<List<Discography>> with the discography
     */
    @Override
    public Task<Discography> getDiscographyByArtistID(String artistID) {
        TaskCompletionSource<Discography> taskCompletionSource = new TaskCompletionSource<>();

        Query query = discographyCollection.whereEqualTo("artistID", artistID);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null && !querySnapshot.isEmpty()) {
                    // The query returned matching documents
                    DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0); // Get the first matching document
                    Discography discography = documentSnapshot.toObject(Discography.class);
                    taskCompletionSource.setResult(discography);
                } else {
                    Log.d("getDiscographyByCategoryID", "No matching documents");
                    taskCompletionSource.setResult(null);
                }
            } else {
                Log.d("getDiscographyByCategoryID", "Error getting documents: " + task.getException());
                taskCompletionSource.setException(task.getException());
            }
        });

        return taskCompletionSource.getTask();
    }

}
