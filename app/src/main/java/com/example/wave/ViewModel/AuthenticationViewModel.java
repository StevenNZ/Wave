package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.AuthenticationErrorUseCase;
import com.example.wave.Domains.AuthenticationUserUseCase;
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

    public void resetAuthenticationError() {
        authenticationErrorUseCase = new AuthenticationErrorUseCase();
        authenticationErrorUseCase.resetAuthenticationError();
    }

    public boolean isLogin() {
        authenticationUserUseCase = new AuthenticationUserUseCase();
        return (authenticationUserUseCase.getAuthenticatedUser().getValue() != null);
    }

    public String getUserID() {
        authenticationUserUseCase = new AuthenticationUserUseCase();
        // can return null
        return (authenticationUserUseCase.getAuthenticatedUser().getValue().getUid());
    }
}
