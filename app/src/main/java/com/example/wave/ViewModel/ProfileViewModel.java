package com.example.wave.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.SignOutUseCase;

public class ProfileViewModel extends ViewModel {
    SignOutUseCase signOutUseCase;

    public void signOutUser() {
        signOutUseCase = new SignOutUseCase();
        signOutUseCase.signOutUser();
    }
}
