package com.yklee.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nex3z.flowlayout.FlowLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EditPlantActivity extends Activity {
    int selectedPlant;
    PlantItem mPlantItem;

    int initialTagsCount;

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
        TextView addLabelBtn = findViewById(R.id.editPlant_addLabelBtn);
        TextView nameView = findViewById(R.id.editPlant_nameInput);
        TextView bNameView = findViewById(R.id.editPlant_bNameInput);
        TextView avgDayView = findViewById(R.id.editPlant_averageDay);

        // --- set views
        nameView.setText(mPlantItem.getName());
        bNameView.setText(mPlantItem.getBotanicalName());
        String avgDayText = mPlantItem.getAverageWaterDay() + "일";
        avgDayView.setText(avgDayText);

        // set tags which PlantItem has
        tagWrap.addView(createTagInView(tags.get(0), false));
        for (int i = 1 ; i < tags.size(); i++) {
            tagWrap.addView(createTagInView(tags.get(i), false));
        }

        initialTagsCount = tagWrap.getChildCount();

        //click events
        addLabelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddLabelDialog();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlowLayout tagWrap = findViewById(R.id.editPlant_tagWrap);
                if(tagWrap.getChildCount() > initialTagsCount) {
                    for (int i = initialTagsCount; i < tagWrap.getChildCount(); i ++) {
                        TagView tag = (TagView) tagWrap.getChildAt(i);
                        String tagName = tag.getTag().toString();
                        tagName = "tag_" + tagName;
                        mPlantItem.setTagItem(tagName);

                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                        DocumentReference docRef = db.collection(firebaseHandler.DB_COL_PLANTS).document(mPlantItem.getDBid());
                        docRef.update("tags", mPlantItem.getTags());
                    }
                }

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
                        FlowLayout tagWrap = findViewById(R.id.editPlant_tagWrap);
                        TagView tag = createTagInView(ret, false);
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
        ret.setMarginRight(12);
        ret.setMarginBottom(12);
        ret.inflate(this);

        return ret;
    }
}
