package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.LoginUseCase;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {
    protected LoginUseCase loginUseCase;
    protected AuthenticationViewModel authenticationViewModel;

    public void signInUser(String email, String password) {
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

    public void resetAuthenticationError() {
        authenticationViewModel = new AuthenticationViewModel();
        authenticationViewModel.resetAuthenticationError();
    }
}
