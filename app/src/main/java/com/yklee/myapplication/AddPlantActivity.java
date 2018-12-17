package com.yklee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class AddPlantActivity extends Activity {
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
        // get views
    }
}
