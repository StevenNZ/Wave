package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.LoginUseCase;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {
    private LoginUseCase loginUseCase;
    private AuthenticationViewModel authenticationViewModel;

    public LoginViewModel() {
        authenticationViewModel = new AuthenticationViewModel();
        loginUseCase = new LoginUseCase();
    }

    public void signInUser(String email, String password) {
        loginUseCase.signInUser(email, password);
    }

    public LiveData<FirebaseUser> getAuthenticatedUser() {
        return authenticationViewModel.getAuthenticatedUser();
    }

    public LiveData<String> getAuthenticationError() {
        return authenticationViewModel.getAuthenticationError();
    }

    public void resetAuthenticationError() {
        authenticationViewModel.resetAuthenticationError();
    }
}
