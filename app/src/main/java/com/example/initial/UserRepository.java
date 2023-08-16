package com.example.initial;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {
    private static UserRepository instance;
    private FirebaseAuth mAuth;
    private MutableLiveData<FirebaseUser> authenticatedUser = new MutableLiveData<>();
    private MutableLiveData<String> authenticationError = new MutableLiveData<>();

    // singleton constructor always private to prevent direct construction calls
    private UserRepository() {
        mAuth = FirebaseAuth.getInstance();
    }

    // static method controlling access of singleton instance
    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private void registerUser(String userName, String email, String password) {
    }

    protected void signInUser(String email, String password) {
            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    if (mAuth.getCurrentUser().isEmailVerified()) {
                        authenticatedUser.postValue(mAuth.getCurrentUser());

                    } else {
                        authenticationError.postValue("Please verify your email");
                        mAuth.signOut();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    authenticationError.postValue(exception.getMessage());
                }
            });
        }

    private void signOutUser() {

    }

    public LiveData<FirebaseUser> getAuthenticatedUser() {
        return authenticatedUser;
    }

    public LiveData<String> getAuthenticationError() {
        return authenticationError;
    }
}

