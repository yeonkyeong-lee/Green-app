package com.yklee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AddPlantActivity extends AppCompatActivity {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplant);

        // find views
        TextView cancelBtn = findViewById(R.id.addPlant_cancelBtn);
        FloatingActionButton fab = findViewById(R.id.addPlant_FAB);

        // click events
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // find views
                EditText name = (EditText)findViewById(R.id.addPlant_nameInput);
                EditText bName = (EditText)findViewById(R.id.addPlant_bNameInput);
                EditText averageDay = (EditText)findViewById(R.id.addPlant_averageDay);
                EditText firstDay = (EditText)findViewById(R.id.addPlant_firstDay);
                EditText lastWaterDay = (EditText)findViewById(R.id.addPlant_lastWaterDay);

                String s_name = name.getText().toString();
                String s_bName = bName.getText().toString();
                //int variable
                int i_avgDay = Integer.parseInt(averageDay.getText().toString());
                String s_fDay = firstDay.getText().toString();
                String s_lDay = lastWaterDay.getText().toString();

                Date d_fDay = new Date();
                Date d_lDay = new Date();
                try {
                    d_fDay = format.parse(s_fDay);
                    d_lDay = format.parse(s_lDay);
                } catch (ParseException e) {
                    Log.d("GREEN-app", "date parse exception : " + e);

                }


                // make constructor
                final PlantItem plant = new PlantItem(s_name, s_bName, i_avgDay, d_fDay, d_lDay);
                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("plants").add(plant);
                SaveCurrData();
                ChangeActivity();
            }
        });
    }
    void ChangeActivity() {
        Intent intent = new Intent(AddPlantActivity.this, PlantListActivity.class);
        startActivity(intent);
    }
    void SaveCurrData() {

    }


}