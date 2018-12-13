package com.yklee.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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

        // create and set list adapter
        mListView = (ListView) findViewById(R.id.plantListView);
        mAdapter = new PlantListAdapter(this,  mPlantList, R.layout.listitem_plantlist);
        mListView.setAdapter(mAdapter);
    }

    void InitData() {
        // init data (temp before db connection)
        // todo : get data from db
        mPlantList = new ArrayList<>();
        mPlantList.add(new PlantListItem("틸다", "틸란드시아 키아네아 ", 2));
        mPlantList.add(new PlantListItem("카스테라", "몬스테라", 14));

    }
}
