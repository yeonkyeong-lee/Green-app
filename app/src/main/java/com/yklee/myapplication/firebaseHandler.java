package com.yklee.myapplication;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

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

                                PlantItem item = new PlantItem(name, bName);
                                item.setAverageCycle(avgCycle);
                                item.setDBid(id);

                                PlantList.add(item);
                            }
                        }
                    }
                });
    }
}
