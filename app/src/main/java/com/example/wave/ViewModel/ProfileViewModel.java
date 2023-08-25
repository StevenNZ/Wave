package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.SignOutUseCase;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private SignOutUseCase signOutUseCase;
    private AuthenticationViewModel authenticationViewModel;

    public ProfileViewModel() {
        authenticationViewModel = new AuthenticationViewModel();
        signOutUseCase = new SignOutUseCase();
    }

    public void signOutUser() {
        signOutUseCase.signOutUser();
    }


    public LiveData<FirebaseUser> getAuthenticatedUser() {
        return authenticationViewModel.getAuthenticatedUser();
    }
}
