package com.example.initial;

import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    SignOutUseCase signOutUseCase;

    protected void signOutUser() {
        signOutUseCase = new SignOutUseCase();
        signOutUseCase.signOutUser();
    }
}
