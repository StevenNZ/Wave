package com.example.initial;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

public class AuthenticationViewModel extends ViewModel {
    protected AuthenticationUserUseCase authenticationUserUseCase;
    protected AuthenticationErrorUseCase authenticationErrorUseCase;

    public LiveData<FirebaseUser> getAuthenticatedUser() {
        authenticationUserUseCase = new AuthenticationUserUseCase();
        return authenticationUserUseCase.getAuthenticatedUser();
    }

    public LiveData<String> getAuthenticationError() {
        authenticationErrorUseCase = new AuthenticationErrorUseCase();
        return authenticationErrorUseCase.getAuthenticationError();
    }
}
