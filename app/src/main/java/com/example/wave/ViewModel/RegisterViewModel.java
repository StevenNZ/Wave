package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.RegisterUseCase;
import com.example.wave.Domains.SignOutUseCase;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {
    private RegisterUseCase registerUseCase;
    private SignOutUseCase signOutUseCase;
    private AuthenticationViewModel authenticationViewModel;

    public RegisterViewModel() {
        authenticationViewModel = new AuthenticationViewModel();
        registerUseCase = new RegisterUseCase();
        signOutUseCase = new SignOutUseCase();
    }

    public void registerUser(String username, String email, String password) {
        registerUseCase.registerUser(username, email, password);
    }

    public LiveData<String> getAuthenticationError() {
        return authenticationViewModel.getAuthenticationError();
    }

    public LiveData<FirebaseUser> getAuthenticatedUser() {
        return authenticationViewModel.getAuthenticatedUser();
    }

    public void signOutUser() {
        signOutUseCase.signOutUser();
    }

    public void resetAuthenticationError() {
        authenticationViewModel.resetAuthenticationError();
    }
}
