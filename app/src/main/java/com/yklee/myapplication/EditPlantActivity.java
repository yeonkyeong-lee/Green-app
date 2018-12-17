package com.yklee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;

public class EditPlantActivity extends Activity {
    int selectedPlant;
    PlantItem mPlantItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editplant);
        Intent intent = getIntent();
        selectedPlant = intent.getIntExtra("selected item", 0);
        mPlantItem = firebaseHandler.PlantList.get(selectedPlant);
        ArrayList<String> tags = mPlantItem.getTags();

        // find views
        FloatingActionButton fab = findViewById(R.id.editPlant_FAB);
        FlowLayout tagWrap = findViewById(R.id.editPlant_tagWrap);

        // --- set views
        // set tags which PlantItem has
        tagWrap.addView(createTagInView(tags.get(0), false));
        for (int i = 1 ; i < tags.size(); i++) {
            tagWrap.addView(createTagInView(tags.get(i), true));
        }

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
        intent.putExtra("selected item", selectedPlant);
        startActivity(intent);
    }
    void SaveCurrData() {

    }

    TagView createTagInView(String tagName, boolean closeBtn) {
        TagView ret = new TagView(this);
        ret.setTagName(tagName.replace("tag_", ""));
        ret.setCloseBtn(closeBtn);
        ret.setMarginRight(12);
        ret.setMarginBottom(12);
        ret.inflate(this);

        return ret;
    }
}
