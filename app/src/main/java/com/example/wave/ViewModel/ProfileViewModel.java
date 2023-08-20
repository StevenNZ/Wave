package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.SignOutUseCase;
import com.google.firebase.auth.FirebaseUser;

public class ProfileViewModel extends ViewModel {
    private SignOutUseCase signOutUseCase;
    private AuthenticationViewModel authenticationViewModel;

    public void signOutUser() {
        if (signOutUseCase == null) {
            signOutUseCase = new SignOutUseCase();
        }
        signOutUseCase.signOutUser();
    }


    public LiveData<FirebaseUser> getAuthenticatedUser() {
        if (authenticationViewModel == null) {
            authenticationViewModel = new AuthenticationViewModel();
        }
        return authenticationViewModel.getAuthenticatedUser();
    }
}
