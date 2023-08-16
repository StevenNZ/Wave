package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.RegisterUseCase;
import com.example.wave.Domains.SignOutUseCase;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends ViewModel {
    protected RegisterUseCase registerUseCase;
    protected SignOutUseCase signOutUseCase;
    protected AuthenticationViewModel authenticationViewModel;

    public void registerUser(String username, String email, String password) {
        registerUseCase = new RegisterUseCase();
        registerUseCase.registerUser(username, email, password);
    }

    public LiveData<String> getAuthenticationError() {
        authenticationViewModel = new AuthenticationViewModel();
        return authenticationViewModel.getAuthenticationError();
    }

    public LiveData<FirebaseUser> getAuthenticatedUser() {
        authenticationViewModel = new AuthenticationViewModel();
        return authenticationViewModel.getAuthenticatedUser();
    }

    public void signOutUser() {
        signOutUseCase = new SignOutUseCase();
        signOutUseCase.signOutUser();
    }

    public void resetAuthenticationError() {
        authenticationViewModel = new AuthenticationViewModel();
        authenticationViewModel.resetAuthenticationError();
    }
}
