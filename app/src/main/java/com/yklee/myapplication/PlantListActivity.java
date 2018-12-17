package com.yklee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PlantListActivity extends Activity {

    ListView mListView;
    public static ArrayList<PlantItem> mPlantList;
    PlantListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantlist);

        mPlantList = firebaseHandler.PlantList;

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
                ChangeActivity_PlantList(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity_AddPlant();
            }
        });
    }

    void ChangeActivity_AddPlant() {
        Intent intent = new Intent(PlantListActivity.this, AddPlantActivity.class);
        startActivity(intent);
    }
    void ChangeActivity_PlantList(int idx) {
        Intent intent = new Intent(PlantListActivity.this, PlantPagerActivity.class);
        intent.putExtra("selected item", idx);
        startActivity(intent);
    }
}
