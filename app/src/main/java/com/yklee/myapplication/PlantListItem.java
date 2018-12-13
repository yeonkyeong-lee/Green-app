package com.yklee.myapplication;

public class PlantListItem {
    private String name;
    private String bName; // botanical name
    private int NextWaterDay; // days to be left until next day to water

    public PlantListItem(String name, String bName, int NextWaterDay) {
        this.name = name;
        this.bName = bName;
        this.NextWaterDay = NextWaterDay;
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
}
