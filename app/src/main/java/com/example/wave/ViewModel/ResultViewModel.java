package com.example.wave.ViewModel;

import androidx.lifecycle.ViewModel;

public class ResultViewModel extends ViewModel {
    protected AuthenticationViewModel authenticationViewModel;

    public boolean isLogin() {
        authenticationViewModel = new AuthenticationViewModel();
        return authenticationViewModel.isLogin();
    }
}
