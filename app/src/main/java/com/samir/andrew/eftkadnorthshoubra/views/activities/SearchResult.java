package com.samir.andrew.eftkadnorthshoubra.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.samir.andrew.eftkadnorthshoubra.R;
import com.samir.andrew.eftkadnorthshoubra.adapter.AdapterSearchResult;
import com.samir.andrew.eftkadnorthshoubra.utlities.HelpMe;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResult extends AppCompatActivity {

    @Bind(R.id.rvSearchResult)
    RecyclerView rvSearchResult;

    private AdapterSearchResult adapterSearchResult;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        if (HelpMe.getInstance(this).isTablet()) {
            mLayoutManager = new GridLayoutManager(this, 2);
        } else {
            mLayoutManager = new GridLayoutManager(this, 1);

        }
        rvSearchResult.setLayoutManager(mLayoutManager);

        adapterSearchResult = new AdapterSearchResult(HomeActivity.modelMemberList, this);
        rvSearchResult.setAdapter(adapterSearchResult);


    }

}
