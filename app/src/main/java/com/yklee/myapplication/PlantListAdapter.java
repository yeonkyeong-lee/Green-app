package com.yklee.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class PlantListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<PlantItem> data;
    private int layout;

    public PlantListAdapter(Context context, ArrayList<PlantItem> data, int layout) {
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public PlantItem getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = inflater.inflate(R.layout.listitem_plantlist, viewGroup, false);
        }

        // set PlantListView data
        TextView nameView = (TextView) view.findViewById(R.id.plantList_plantName);
        TextView bNameView = (TextView) view.findViewById(R.id.plantList_botanicalName);
        TextView waterDayView = (TextView) view.findViewById(R.id.plantList_nextWaterDay);

        PlantItem listItem = data.get(i);

        nameView.setText(listItem.getName());
        bNameView.setText(listItem.getBotanicalName());
        String wDay = String.valueOf(listItem.getNextWaterDay()) + "일 후 ";
        waterDayView.setText(wDay);

        return view;
    }
}

/*
* ref.
* https://medium.com/android-develop-android/android%EA%B0%9C%EB%B0%9C-5-listview%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-%EB%A6%AC%EC%8A%A4%ED%8A%B8%EB%A7%8C%EB%93%A4%EA%B8%B0-215b9693d33b
* http://recipes4dev.tistory.com/43?category=605791
* */