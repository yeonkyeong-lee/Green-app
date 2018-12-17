package com.yklee.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMemoActivity extends Activity {
    int selectedPlant;
    PlantItem mPlantItem;

    LinearLayout contentWrap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmemo);
        Intent intent = getIntent();
        selectedPlant = intent.getIntExtra("selected item", 0);
        mPlantItem = firebaseHandler.PlantList.get(selectedPlant);
        ArrayList<String> tags = mPlantItem.getTags();

        // ---- find views
        ImageView closeBtn = findViewById(R.id.addMemo_closeBtn);
        FloatingActionButton fab = findViewById(R.id.addMemo_FAB);
        TextView titleView = findViewById(R.id.addMemo_title);
        contentWrap = findViewById(R.id.addMemo_contentWrap);

        // ---- set views
        // set titleString
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        String today = df.format(new Date());
        String[] today_split = today.split("-");
        if(today_split[1].charAt(0) == '0') today_split[1] = today_split[1].substring(1);
        if(today_split[2].charAt(0) == '0') today_split[2] = today_split[2].substring(1);
        String finalTodayText = today_split[1] + "월 " + today_split[2] + "일의 기록";
        titleView.setText(finalTodayText);

        // set tags which PlantItem has
        // tags[0] is always "tag_물준날"
        contentWrap.addView(createTagInView(tags.get(0), false));
        // set other tags
        for (int i = 1; i < tags.size(); i++) {
            contentWrap.addView(createTagInView(tags.get(i), false));
            contentWrap.addView(createEditText(tags.get(i)));
        }

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
                SaveCurrData();
            }
        });

    }
    void ChangeActivity() {
        Intent intent = new Intent(AddMemoActivity.this, PlantPagerActivity.class);
        intent.putExtra("selected item", selectedPlant);
        startActivity(intent);
    }
    void SaveCurrData() {
        int childCount = contentWrap.getChildCount();
        for (int i = 0; i < childCount; i ++) {
            View v = contentWrap.getChildAt(i);
            if(v instanceof EditText) {
                EditText form = (EditText) v;
                String tagName = form.getTag().toString();
                String content = form.getText().toString();
                if(!content.equals("")) {
                    mPlantItem.setMemoItem(new MemoItem(tagName, new Date(), content)); // save to local list
                    //save to firebase
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                    String today = format.format(new Date());
                    firebaseHandler.addMemoItem(mPlantItem.getDBid(), tagName, today, content);
                }
            }
        }

    }
    TagView createTagInView(String tagName, boolean closeBtn) {
        TagView ret = new TagView(this);
        ret.setTagName(tagName.replace("tag_", ""));
        ret.setCloseBtn(closeBtn);
        ret.setMarginTop(24);
        ret.inflate(this);

        return ret;
    }

    EditText createEditText(String tagName) {
        ContextThemeWrapper themeContext = new ContextThemeWrapper(this, R.style.EditTextStyle);
        EditText form = new EditText(themeContext);
        form.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        form.setHint(R.string.editText_hint);
        form.setTextColor(getResources().getColor(R.color.TextColor));
        form.setHintTextColor(getResources().getColor(R.color.TextColorGray));
        form.setTag(tagName); // firebase 에 저장할 때 어떤 태그 관련인지 알기 위해 태그 세팅

        int marginTop = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 12, getResources()
                        .getDisplayMetrics());
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) form.getLayoutParams();
        params.setMargins(0, marginTop, 0, 0);
        form.setLayoutParams(params);

        return form;
    }
}
