package com.yklee.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.util.AttributeSet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nex3z.flowlayout.FlowLayout;

public class AddPlantActivity extends AppCompatActivity {
    final int INITIAL_TAG_COUNT = 2;

    static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplant);

        // find views
        TextView cancelBtn = findViewById(R.id.addPlant_cancelBtn);
        FloatingActionButton fab = findViewById(R.id.addPlant_FAB);
        TextView addLabelBtn = findViewById(R.id.addPlant_addLabelBtn);

        // click events
        addLabelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddLabelDialog();
            }
        });

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

                // make constructor
                final PlantItem plant = new PlantItem(s_name, s_bName, i_avgDay, s_fDay, s_lDay);
                final FirebaseFirestore db = FirebaseFirestore.getInstance();

                // set tags
                FlowLayout tagWrap = findViewById(R.id.addPlant_tagWrap);
                if(tagWrap.getChildCount() > INITIAL_TAG_COUNT) {
                    for (int i = 2; i < tagWrap.getChildCount(); i ++) {
                        TagView tag = (TagView) tagWrap.getChildAt(i);
                        String tagName = tag.getTag().toString();
                        tagName = "tag_" + tagName;
                        plant.setTagItem(tagName);
                    }
                }

                db.collection("plants").add(plant);
                firebaseHandler.PlantList.add(plant);
                ChangeActivity();
            }
        });
    }
    void ChangeActivity() {
        Intent intent = new Intent(AddPlantActivity.this, PlantListActivity.class);
        startActivity(intent);
    }

    void showAddLabelDialog()
    {
        final EditText edittext = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("라벨 추가");
        builder.setMessage("추가할 라벨을 입력하세요.");
        builder.setView(edittext);
        builder.setPositiveButton("입력",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String ret = edittext.getText().toString();
                        FlowLayout tagWrap = findViewById(R.id.addPlant_tagWrap);
                        TagView tag = createTagInView(ret, true);
                        tag.setTag(ret);
                        tagWrap.addView(tag);
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    TagView createTagInView(String tagName, boolean closeBtn) {
        TagView ret = new TagView(this);
        ret.setTagName(tagName.replace("tag_", ""));
        ret.setCloseBtn(closeBtn);
        ret.setMarginBottom(12);
        ret.setMarginRight(12);
        ret.inflate(this);

        return ret;
    }

}