package com.example.wave.Domains;

import androidx.lifecycle.LiveData;

import com.example.wave.Dataproviders.UserProvider;
import com.example.wave.Repository.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationUserUseCase implements UserProvider {

    @Override
    public UserRepository getInstance() {
        return null;
    }

    @Override
    public void registerUser(String userName, String email, String password) {

    }

    @Override
    public void signInUser(String email, String password) {

    }

    @Override
    public void signOutUser() {

    }

    @Override
    public LiveData<FirebaseUser> getAuthenticatedUser() {
        return UserRepository.getInstance().getAuthenticatedUser();
    }

    @Override
    public LiveData<String> getAuthenticationError() {
        return null;
    }

    @Override
    public void resetAuthenticationError() {

    }

    public boolean isLogin() {
        return (this.getAuthenticatedUser().getValue() != null);
    }

    public String getUserID() {
        // can return null
        return (this.getAuthenticatedUser().getValue().getUid());
    }

}
