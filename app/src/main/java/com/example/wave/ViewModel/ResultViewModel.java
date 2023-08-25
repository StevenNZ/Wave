package com.example.wave.ViewModel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class ResultViewModel extends ViewModel {
    private AuthenticationViewModel authenticationViewModel;

    public ResultViewModel() {
        authenticationViewModel = new AuthenticationViewModel();
    }

    public boolean isLogin() {
        return authenticationViewModel.isLogin();
    }
}
