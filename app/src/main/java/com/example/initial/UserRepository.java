package com.example.initial;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

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

    protected void registerUser(String userName, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(userName).build();

                            user.updateProfile(profileUpdates);

                            sendVerifyEmail();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            authenticationError.postValue(task.getException().getMessage());
                        }
                    }
                });
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

    protected void signOutUser() {
        authenticatedUser.postValue(null);
        mAuth.signOut();
    }

    protected LiveData<FirebaseUser> getAuthenticatedUser() {
        return authenticatedUser;
    }

    protected LiveData<String> getAuthenticationError() {
        return authenticationError;
    }

    protected void resetAuthenticationError() {
        authenticationError.postValue(null);
    }

    private void sendVerifyEmail() {
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    authenticationError.postValue(null);
                    authenticatedUser.postValue(mAuth.getCurrentUser());
                } else {
                    authenticationError.postValue(task.getException().getMessage());
                }
            }
        });
    }
}

