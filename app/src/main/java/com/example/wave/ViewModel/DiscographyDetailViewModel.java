package com.example.wave.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.GetDiscographyUseCase;
import com.example.wave.Entities.Discography;
import com.google.android.gms.tasks.Task;

public class DiscographyDetailViewModel extends ViewModel {

    private GetDiscographyUseCase getDiscographyUseCase;

    public DiscographyDetailViewModel() {
        getDiscographyUseCase = new GetDiscographyUseCase();
    }

    public Task<Discography> getDiscographyDetail(String id) {
        return getDiscographyUseCase.getDiscographyByDiscographyID(id);
    }

    public void addViewToDiscography(String discographyID) {
        getDiscographyUseCase.addViewToDiscography(discographyID);
    }
}
