package com.example.wave.Domains;

import androidx.lifecycle.LiveData;

import com.example.wave.Dataproviders.UserProvider;
import com.example.wave.Repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class RegisterUseCase implements UserProvider {
    @Override
    public UserRepository getInstance() {
        return null;
    }

    @Override
    public void registerUser(String userName, String email, String password) {
        UserRepository.getInstance().registerUser(userName, email, password);
    }

    @Override
    public void signInUser(String email, String password) {

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

    @Override
    public void resetAuthenticationError() {

    }
}
