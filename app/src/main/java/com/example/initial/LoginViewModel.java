package com.example.initial;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {
    protected LoginUseCase loginUseCase;
    protected AuthenticationViewModel authenticationViewModel;

    protected void signInUser(String email, String password) {
        loginUseCase = new LoginUseCase();
        loginUseCase.signInUser(email, password);
    }

    public LiveData<FirebaseUser> getAuthenticatedUser() {
        authenticationViewModel = new AuthenticationViewModel();
        return authenticationViewModel.getAuthenticatedUser();
    }

    public LiveData<String> getAuthenticationError() {
        authenticationViewModel = new AuthenticationViewModel();
        return authenticationViewModel.getAuthenticationError();
    }
}
