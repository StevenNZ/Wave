package com.example.wave.ViewModel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private AuthenticationViewModel authenticationViewModel;

    public MainViewModel() {
        authenticationViewModel = new AuthenticationViewModel();
    }

    public boolean isLogin() {
        return authenticationViewModel.isLogin();
    }
}
