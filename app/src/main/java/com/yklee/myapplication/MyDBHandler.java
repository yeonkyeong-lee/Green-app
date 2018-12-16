package com.yklee.myapplication;

import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;

import java.time.LocalDate;
import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "plantDB.db";
    public static final String TABLE_PLANTS = "plants";

    private static LocalDate nextDay;
    private static LocalDate averageDay;
    private static LocalDate firstDay;
    private static LocalDate lastDay;
    private static ArrayList<MemoItem> memos;
    private static ArrayList<String> tags;

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PLANTNAME = "plantName";
    private static final String COLUMN_B_PLANTNAME = "b_plantName";
    private static final LocalDate COLUMN_NEXTDAY = nextDay;
    private static final LocalDate COLUMN_AVERAGEDAY = averageDay;
    private static final LocalDate COLUMN_FIRSTDAY = firstDay;
    private static final LocalDate COLUMN_LASTDAY = lastDay;
    private static final ArrayList<MemoItem> COLUMN_MEMOS = memos;
    private static final ArrayList<String> COLUMN_TAGS = tags;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PLANTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PLANTNAME + " TEXT," + COLUMN_B_PLANTNAME + " String,"
                + COLUMN_NEXTDAY + " DATE," + COLUMN_AVERAGEDAY + " DATE," + COLUMN_FIRSTDAY + " DATE," + COLUMN_FIRSTDAY + " DATE," + COLUMN_LASTDAY + " DATE,"
                + COLUMN_MEMOS + " ARRAYLIST<MEMOITME>," + COLUMN_TAGS + " ARRAYLIST<STRING>"
                + ")";
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANTS);
        onCreate(db);
    }

    public void addProduct(PlantItem plantItem){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLANTNAME, plantItem.getName());
        values.put(COLUMN_PLANTNAME, plantItem.getBotanicalName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PLANTS, null, values);
        db.close();
    }

    @TargetApi(Build.VERSION_CODES.O)
    public PlantItem findProduct(String name){
        String query = "SELECT * FROM" + TABLE_PLANTS + " WHERE " + COLUMN_PLANTNAME + " =  \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        PlantItem plant =  new PlantItem();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            plant.setID(Integer.parseInt(cursor.getString(0)));
            plant.setName(cursor.getString(1));
            plant.setBotanicalName(cursor.getString(2));
            plant.setNextWaterDay(LocalDate.parse(cursor.getString(3)));
            cursor.close();
        }
        else {
            plant = null;
        }
        db.close();
        return plant;
    }
    public boolean deleteProduct(String name){

        boolean result = false;

        String query = "SELECT * FROM " + TABLE_PLANTS + " WHERE " + COLUMN_PLANTNAME + " =  \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        PlantItem plant = new PlantItem();

        if(cursor.moveToFirst()){
            plant.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PLANTS, COLUMN_ID + " = ?", new String[] { String.valueOf(plant.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return  result;
    }
}
