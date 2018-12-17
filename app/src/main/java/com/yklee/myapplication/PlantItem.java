package com.yklee.myapplication;
import java.util.ArrayList;


public class PlantItem {
    private int id;
    private String name;
    private String bName; // botanical name
    private int nextWaterDay; // days to be left until next day to water
    private int averageWaterDay;
    private String firstDay;
    private String lastWaterDay;
    private ArrayList<MemoItem> memos = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();
    private int averageCycle;
    private String DBid;


    public PlantItem(){

    }
    public PlantItem(String name, String bName) {
        this.name = name;
        this.bName = bName;
    }

    public PlantItem(String name, String bName, int averageWaterDay, String firstDay, String lastWaterDay) {
        this.name = name;
        this.bName = bName;
        this.averageWaterDay = averageWaterDay;
        this.firstDay = firstDay;
        this.lastWaterDay = lastWaterDay;

        tags.add("tag_물준날");
//        memos = new ArrayList<>();
    }

    /*--- getter ---*/
    public int getID(){return this.id;}
    public String getName() {
        return this.name;
    }
    public String getBotanicalName() {
        return this.bName;
    }
    public int getNextWaterDay() {
        return this.nextWaterDay;
    }
    public int getAverageWaterDay() {
        return this.averageWaterDay;
    }
    public String getFirstDay() {
        return this.firstDay;
    }
    public String getLastWaterDay() {
        return this.lastWaterDay;
    }
    public ArrayList<MemoItem> getMemos() { return this.memos; }
    public MemoItem getMemoItem(int i) {
        return this.memos.get(i);
    }
    public ArrayList<String> getTags(){return this.tags;}
    public String getTagItem(int i){return this.tags.get(i);}

    public int getAverageCycle() {
        return averageCycle;
    }

    public String getDBid() {
        return DBid;
    }

    /*-- setter --*/
    public void setID(int id){ this.id = id;}
    public void setName(String name){ this.name = name;}
    public void setBotanicalName(String bName){this.bName = bName;}
    public void setNextWaterDay(int day){this.nextWaterDay = day;}
    public void setAverageWaterDay(int day){this.averageWaterDay = day;}
    public void setFirstDay(String date){this.firstDay = date;}
    public void setLastWaterDay(String date){this.lastWaterDay = date;}
    public void setMemos(ArrayList<MemoItem> memos){this.memos = memos;}
    public void setMemoItem(MemoItem item){this.memos.add(item);}
    public void setTags(ArrayList<String> tags){this.tags = tags;}
    public void setTagItem(String item){this.tags.add(item);}
    public void setAverageCycle(int cycle){this.averageCycle = cycle;}
    public void setDBid(String DBid) {this.DBid = DBid;}
}
