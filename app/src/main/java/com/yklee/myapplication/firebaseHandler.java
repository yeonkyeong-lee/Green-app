package com.yklee.myapplication;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class firebaseHandler {

    public static final String DB_NAMESPACE_TAG = "tag_";

    public static final String DB_COL_PLANTS = "plants";
    public static final String DB_FIELD_NAME = "name";
    public static final String DB_FIELD_BNAME = "bName";
    public static final String DB_FIELD_FIRSTDAY = "firstDay";
    public static final String DB_FIELD_LASTWATERDAY = "lastWaterDay";
    public static final String DB_FIELD_AVERAGECYCLE = "averageCycle";
    public static final String DB_FIELD_TAGNAMES = "tagNames";

    public static ArrayList<PlantItem> PlantList;

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static void getAllData() {
        PlantList = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(DB_COL_PLANTS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getId();
                                String name = document.getData().get(DB_FIELD_NAME).toString();
                                String bName = document.getData().get(DB_FIELD_BNAME).toString();
                                int avgCycle = Integer.parseInt(document.getData().get(DB_FIELD_AVERAGECYCLE).toString());
                                String firstDay = document.getData().get(DB_FIELD_FIRSTDAY).toString();
                                String lastWaterDay = document.getData().get(DB_FIELD_LASTWATERDAY).toString();
                                Date firstDay_date = new Date();
                                Date lastWaterDay_date = new Date();
                                try {
                                    firstDay_date =  format.parse(firstDay);
                                    lastWaterDay_date = format.parse(lastWaterDay);
                                } catch (ParseException e) {
                                    Log.d("GREEN-app", "date parse exception : " + e);
                                }
                                ArrayList<String> tags = StringToArray(document.getData().get(DB_FIELD_TAGNAMES).toString());
                                // tag이름에 따라 메모 내용 가져오기
                                ArrayList<MemoItem> memos = new ArrayList<>();
                                for (int i =1 ;i < tags.size(); i ++) { //tag[0] is always tag_물준날
                                    Log.d("green tag", tags.get(i));
                                    String rawData = document.getData().get(tags.get(i)).toString();
                                    ArrayList<MemoItem> memos_item = StringToMemos(rawData, tags.get(i));
                                    memos.addAll(memos_item);
                                }

                                PlantItem item = new PlantItem(name, bName);
                                item.setAverageCycle(avgCycle);
                                item.setDBid(id);
                                item.setFirstDay(firstDay_date);
                                item.setLastWaterDay(lastWaterDay_date);
                                item.setTags(tags);
                                item.setMemos(memos);

                                PlantList.add(item);
                            }
                        }
                    }
                });
    }
    static ArrayList<String> StringToArray(String input) {
        input = input.substring(1, input.length()-1);
        ArrayList<String> res = new ArrayList<>(Arrays.asList(input.split(", ")));

        return res;
    }
    static ArrayList<MemoItem> StringToMemos(String input, String tagname) {
        input = input.substring(1, input.length()-1);
        Log.d("green",input);
        ArrayList<MemoItem> res = new ArrayList<>();
        String[] splitRes = input.split(", ");
        for (int i = 0; i < splitRes.length; i++) {
            String[] splitRes_item = splitRes[i].split("=");
            try{
                Date date = format.parse(splitRes_item[0]);
                MemoItem item = new MemoItem(tagname, date, splitRes_item[1]);
                res.add(item);
            } catch (ParseException e) {
                Log.d("GREEN-app", "date parse exception : " + e);
            }
        }
        return res;
    }
}
