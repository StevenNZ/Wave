package com.example.wave.Dataproviders;

import androidx.lifecycle.LiveData;

import com.example.wave.Repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public interface UserProvider {

    UserRepository getInstance();
    void registerUser(String userName, String email, String password);
    void signInUser(String email, String password);
    void signOutUser();
    LiveData<FirebaseUser> getAuthenticatedUser();
    LiveData<String> getAuthenticationError();
    void resetAuthenticationError();
}
