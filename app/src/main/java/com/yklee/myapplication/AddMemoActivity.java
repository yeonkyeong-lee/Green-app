package com.yklee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddMemoActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmemo);

        // find views
        ImageView closeBtn = findViewById(R.id.addMemo_closeBtn);
        FloatingActionButton fab = findViewById(R.id.addMemo_FAB);

        // click event
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity();
            }
        });

    }
    void ChangeActivity() {
        Intent intent = new Intent(AddMemoActivity.this, PlantPagerActivity.class);
        startActivity(intent);
    }
    void SaveCurrData() {

    }


}
