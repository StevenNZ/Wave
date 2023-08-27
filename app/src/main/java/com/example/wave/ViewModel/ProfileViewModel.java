package com.example.wave.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wave.Activities.MainActivity;
import com.example.wave.Domains.GetOrderHistoryUseCase;
import com.example.wave.Domains.SignOutUseCase;
import com.example.wave.Entities.CartOrder;
import com.example.wave.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private SignOutUseCase signOutUseCase;
    private AuthenticationViewModel authenticationViewModel;

    private GetOrderHistoryUseCase getOrderHistoryUseCase;

    public ProfileViewModel() {
        authenticationViewModel = new AuthenticationViewModel();
        signOutUseCase = new SignOutUseCase();
        getOrderHistoryUseCase = new GetOrderHistoryUseCase();
    }

    public void signOutUser() {
        signOutUseCase.signOutUser();
    }


    public LiveData<FirebaseUser> getAuthenticatedUser() {
        return authenticationViewModel.getAuthenticatedUser();
    }

    public Task<List<List<CartOrder>>> getOrderHistory(String userId){
        return getOrderHistoryUseCase.getOrderHistory(userId);

    }
}
