package com.example.wave.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.GetDiscographyUseCase;
import com.example.wave.Entities.Discography;
import com.google.android.gms.tasks.Task;

public class DiscographyDetailViewModel extends ViewModel {

    private GetDiscographyUseCase getDiscographyUseCase;

    public Task<Discography> getDiscographyDetail(String id) {
        if (getDiscographyUseCase == null) {
            getDiscographyUseCase = new GetDiscographyUseCase();
        }
        return getDiscographyUseCase.getDiscographyByDiscographyID(id);
    }

    public void addViewToDiscography(String discographyID) {
        if (getDiscographyUseCase == null) {
            getDiscographyUseCase = new GetDiscographyUseCase();
        }
        getDiscographyUseCase.addViewToDiscography(discographyID);
    }
}
