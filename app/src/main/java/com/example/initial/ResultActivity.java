package com.example.initial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        List<Popular> resultList = SearchUseCase.getSearchedDiscography();
        PopularAdaptor resultAdapater = new PopularAdaptor (this, R.layout.popular_list_item, resultList);
        ListView listView = findViewById(R.id.result_list_view);
        listView.setAdapter(resultAdapater);
    }
}