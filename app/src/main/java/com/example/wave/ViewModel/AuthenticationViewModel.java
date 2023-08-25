package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.AuthenticationErrorUseCase;
import com.example.wave.Domains.AuthenticationUserUseCase;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationViewModel extends ViewModel {
    private AuthenticationUserUseCase authenticationUserUseCase;
    private AuthenticationErrorUseCase authenticationErrorUseCase;

    public AuthenticationViewModel() {
        authenticationUserUseCase = new AuthenticationUserUseCase();
        authenticationErrorUseCase = new AuthenticationErrorUseCase();
    }

    public LiveData<FirebaseUser> getAuthenticatedUser() {
        return authenticationUserUseCase.getAuthenticatedUser();
    }

    public LiveData<String> getAuthenticationError() {
        return authenticationErrorUseCase.getAuthenticationError();
    }

    public void resetAuthenticationError() {
        authenticationErrorUseCase.resetAuthenticationError();
    }

    public boolean isLogin() {
        return (authenticationUserUseCase.getAuthenticatedUser().getValue() != null);
    }

    public String getUserID() {
        // can return null
        return (authenticationUserUseCase.getAuthenticatedUser().getValue().getUid());
    }
}
