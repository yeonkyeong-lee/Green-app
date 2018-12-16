package com.yklee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class PlantMainFragment extends Fragment {
    public static PlantMainFragment newInstance() {
        return new PlantMainFragment();
    }

    ListView mListView;
    MemoListAdapter mAdapter;
    PlantItem mPlantItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_plantmain, container, false);

        InitData();

        // find views
        mListView = v.findViewById(R.id.MemoListView);
        ImageView navIcon = v.findViewById(R.id.plantPage_navIcon);
        FloatingActionButton fab = v.findViewById(R.id.plantPage_FAB);
        ImageView profile = v.findViewById(R.id.plantPage_profile);

        // create and set list adapter
        mAdapter = new MemoListAdapter(this.getActivity(), mPlantItem.getMemos(), R.layout.listitem_memolist);
        mListView.setAdapter(mAdapter);

        // click events
        navIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity_toList();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity_toAddMemo();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity_toEditPlant();
            }
        });

        return v;
    }


    void InitData() {
        // init data (temp before db connection)
        // todo : get data from db
        mPlantItem = new PlantItem("식물 이름", "학명");
        mPlantItem.AddMemo(new MemoItem("메모", new Date(), "내용"));
        mPlantItem.AddMemo(new MemoItem("메모2", new Date(), "내용2"));
    }

    void ChangeActivity_toList() {
        Intent intent = new Intent(this.getActivity(), PlantListActivity.class);
        startActivity(intent);
    }
    void ChangeActivity_toAddMemo() {
        Intent intent = new Intent(this.getActivity(), AddMemoActivity.class);
        startActivity(intent);
    }
    void ChangeActivity_toEditPlant() {
        Intent intent = new Intent(this.getActivity(), EditPlantActivity.class);
        startActivity(intent);
    }
}
