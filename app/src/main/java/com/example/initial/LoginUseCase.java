package com.example.initial;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

public class LoginUseCase implements UserProvider{

    @Override
    public UserRepository getInstance() {
        return null;
    }

    @Override
    public void registerUser(String userName, String email, String password) {

    }

    @Override
    public void signInUser(String email, String password) {
         UserRepository.getInstance().signInUser(email, password);
    }

    @Override
    public void signOutUser() {

    }

    @Override
    public LiveData<FirebaseUser> getAuthenticatedUser() {
        return null;
    }

    @Override
    public LiveData<String> getAuthenticationError() {
        return null;
    }
}
