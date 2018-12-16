package com.yklee.myapplication;

import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;

public class PlantItem {
    private String name;
    private String bName; // botanical name
    private int NextWaterDay = 0; // days to be left until next day to water
    private int averageWaterDay;
    private Date firstDay;
    private Date lastWaterDay;
    private ArrayList<MemoItem> memos;

    public PlantItem(String name, String bName) {
        this.name = name;
        this.bName = bName;
       // this.NextWaterDay = NextWaterDay;
        memos = new ArrayList<>();
    }

    /*--- getter ---*/
    public String getName() {
        return name;
    }
    public String getBotanicalName() {
        return bName;
    }
    public int getNextWaterDay() {
        return NextWaterDay;
    }
    public int getAverageWaterDay() {
        return averageWaterDay;
    }
    public Date getFirstDay() {
        return firstDay;
    }
    public Date getLastWaterDay() {
        return lastWaterDay;
    }
    public ArrayList<MemoItem> getMemos() {
        return memos;
    }
    public MemoItem getMemoItem(int i) {
        return memos.get(i);
    }

    /*-- setter --*/
    public void AddMemo(MemoItem item) {
        memos.add(item);
    }
    public void setNextWaterDay(int day) {
        this.NextWaterDay = day;
    }
}
