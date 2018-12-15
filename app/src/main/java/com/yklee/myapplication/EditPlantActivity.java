package com.yklee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

public class EditPlantActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editplant);

        // find views
        FloatingActionButton fab = findViewById(R.id.editPlant_FAB);

        //click events
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveCurrData();
                ChangeActivity();
            }
        });
    }

    void ChangeActivity() {
        Intent intent = new Intent(EditPlantActivity.this, PlantPagerActivity.class);
        startActivity(intent);
    }
    void SaveCurrData() {

    }
}
