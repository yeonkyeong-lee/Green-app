package com.yklee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PlantListActivity extends Activity {
    ListView mListView;
    ArrayList<PlantListItem> mPlantList;
    PlantListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantlist);

        InitData();

        //find views
        mListView = (ListView) findViewById(R.id.plantListView);
        FloatingActionButton fab = findViewById(R.id.plantList_FAB);

        // create and set list adapter
        mAdapter = new PlantListAdapter(this,  mPlantList, R.layout.listitem_plantlist);
        mListView.setAdapter(mAdapter);

        //click events
        // change activity when item click
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PlantListActivity.this, PlantPagerActivity.class);
                intent.putExtra("selected item", i);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity();
            }
        });
    }

    void InitData() {
        // init data (temp before db connection)
        // todo : get data from db
        mPlantList = new ArrayList<>();
        mPlantList.add(new PlantListItem("틸다", "틸란드시아 키아네아 ", 2));
        mPlantList.add(new PlantListItem("카스테라", "몬스테라", 14));
    }

    void ChangeActivity() {
        Intent intent = new Intent(PlantListActivity.this, AddPlantActivity.class);
        startActivity(intent);
    }
}
