package com.example.wave.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.wave.R;
import com.example.wave.Adaptor.PopularAdaptor;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private SearchView searchView;
    private PopularAdaptor resultAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        List<Popular> resultList = SearchUseCase.getSearchedDiscography();
        resultAdapater = new PopularAdaptor (this, R.layout.popular_list_item, resultList);
        ListView listView = findViewById(R.id.result_list_view);
        listView.setAdapter(resultAdapater);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.results_menu, menu);

        MenuItem resultsMenu = menu.findItem(R.id.result_search_view);
        searchView = (SearchView) resultsMenu.getActionView();

        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //when the user presses enter what happens.
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //any changes (autocomplete)


                List<Popular> filter = SearchUseCase.getSearchedDiscography();
                List<Popular> filteredList = new ArrayList<>();

                Log.d("SearchDebug", "onQueryTextChange: newText = " + newText);
                Log.d("SearchDebug", "onQueryTextChange: newText = " + filter);


                for (Popular popular: filter){
                    //if album name in newText or artist name in new text
                    if(popular.getAlbumName().toLowerCase().contains(newText.toLowerCase())
                    || popular.getAlbumArtist().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(popular);
                    }
                }
                if(filteredList.isEmpty()){
                    //this makes sure that when the list is empty we don't do anything
                    // can refactor to make better but idc


                }else{
                    Log.d("SearchDebug", "onQueryTextChange: newText = " + newText);
                    Log.d("SearchDebug", "onQueryTextChange: filtered = " + filteredList);

                    resultAdapater.setFilteredList(filteredList);
                }
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
