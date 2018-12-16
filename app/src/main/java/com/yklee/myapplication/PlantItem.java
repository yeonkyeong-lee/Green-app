package com.yklee.myapplication;

import android.view.MenuItem;

import java.time.LocalDate;
import java.util.ArrayList;


public class PlantItem {
    private int id;
    private String name;
    private String bName; // botanical name
    private LocalDate nextWaterDay; // days to be left until next day to water
    private LocalDate averageWaterDay;
    private LocalDate firstDay;
    private LocalDate lastWaterDay;
    private ArrayList<MemoItem> memos;
    private ArrayList<String> tags;

    public PlantItem(){

    }

    public PlantItem(String name, String bName) {
        this.name = name;
        this.bName = bName;
       // this.NextWaterDay = NextWaterDay;
        memos = new ArrayList<>();
    }

    /*--- getter ---*/
    public int getID(){return this.id;}
    public String getName() {
        return this.name;
    }
    public String getBotanicalName() {
        return this.bName;
    }
    public LocalDate getNextWaterDay() {
        return this.nextWaterDay;
    }
    public LocalDate getAverageWaterDay() {
        return this.averageWaterDay;
    }
    public LocalDate getFirstDay() {
        return this.firstDay;
    }
    public LocalDate getLastWaterDay() {
        return this.lastWaterDay;
    }
    public ArrayList<MemoItem> getMemos() { return this.memos; }
    public MemoItem getMemoItem(int i) {
        return this.memos.get(i);
    }
    public ArrayList<String> getTags(){return this.tags;}
    public String getTagItem(int i){return this.tags.get(i);}

    /*-- setter --*/
    public void setID(int id){ this.id = id;}
    public void setName(String name){ this.name = name;}
    public void setBotanicalName(String bName){this.bName = bName;}
    public void setNextWaterDay(LocalDate date){this.nextWaterDay = date;}
    public void setAverageWaterDay(LocalDate date){this.averageWaterDay = date;}
    public void setFirstDay(LocalDate date){this.firstDay = date;}
    public void setLastWaterDay(LocalDate date){this.lastWaterDay = date;}
    public void setMemos(ArrayList<MemoItem> memos){this.memos = memos;}
    public void setMemoItem(MemoItem item){this.memos.add(item);}
    public void setTags(ArrayList<String> tags){this.tags = tags;}
    public void setTagItem(String item){this.tags.add(item);}
}
