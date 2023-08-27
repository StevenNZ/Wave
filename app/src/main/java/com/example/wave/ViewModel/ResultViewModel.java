package com.example.wave.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.wave.Domains.GetDiscographyUseCase;
import com.example.wave.Entities.Discography;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

public class ResultViewModel extends ViewModel {
    private AuthenticationViewModel authenticationViewModel;

    private GetDiscographyUseCase getDiscographyUseCase;

    private int minPrice = 0;
    private int maxPrice = 1000000;
    private String sortBy = "Default";



    public ResultViewModel() {

        authenticationViewModel = new AuthenticationViewModel();
        getDiscographyUseCase = new GetDiscographyUseCase();
    }

    public boolean isLogin() {
        return authenticationViewModel.isLogin();
    }


    public Task<List<Discography>> getDiscographyListByCategory(String categoryID) throws Exception {
        return getDiscographyUseCase.getDiscographyByCategoryID(categoryID);
    }

    public Task<List<Discography>> getDiscographyListBySearch(String searchString) throws Exception {
        return getDiscographyUseCase.getDiscographyBySearch(searchString);
    }

    private List<Discography> getFilteredDiscography(List<Discography> unfilteredDiscographyList){
        List<Discography> discographyList = new ArrayList<>();
        for (Discography discography : unfilteredDiscographyList) {
            if (Integer.parseInt(discography.getPrice()) >= minPrice && Integer.parseInt(discography.getPrice()) <= maxPrice) {
                discographyList.add(discography);
            }
        }

        switch(sortBy){
            case "Default":
                break;
            case "$ - $$$":
                Collections.sort(discographyList, Comparator.comparing(Discography::getPrice));
                break;
            case "$$$ - $":
                Collections.sort(discographyList, Comparator.comparing(Discography::getPrice).reversed());
                break;
            case "A - Z":
                Collections.sort(discographyList, Comparator.comparing(Discography::getReleaseDate));
                break;
            case "Z - A":
                Collections.sort(discographyList, Comparator.comparing(Discography::getReleaseDate).reversed());
                break;
        }
        return discographyList;
    }






    /**
     * Getter for minPrice
     * @return minPrice
     */
    public int getMinPrice() {
        return minPrice;
    }

    /**
     * Setter for minPrice
     * @param minPrice
     */
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * Getter for maxPrice
     * @return maxPrice
     */
    public int getMaxPrice() {
        return maxPrice;
    }

    /**
     * Setter for maxPrice
     * @param maxPrice
     */
    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Getter for SortBy
     * @return SortBy
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Setter for SortBy
     * @param sortBy
     */
    public void setSortBy(String sortBy) {
        sortBy = sortBy;
    }
}
