package com.yklee.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
* define customView (for tag widget)
* ref.
* http://snowdeer.github.io/android/2017/08/14/custom-view-example/
* */

public class TagView extends LinearLayout {
    private String tagName;
    private boolean isCloseBtn;
    private Context context;
    private int marginTop = 0;
    private int marginBottom = 0;
    private int marginRight = 0;
    private int marginLeft = 0;

    public TagView(Context context) {
        super(context);
    }

    public TagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflateViews(context, attrs);
    }

    public TagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        inflateViews(context, attrs);
    }

    public void inflateViews(Context context, AttributeSet attrs) {
        // view inflate
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tag_view, this);

        setOrientation(LinearLayout.HORIZONTAL);
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        if(attrs != null) {
            //tagName attr
            tagName = context.obtainStyledAttributes(attrs, R.styleable.TagView).getString(R.styleable.TagView_tagName);
            TextView tagNameView = (TextView) findViewById(R.id.tagView_tagName);
            tagNameView.setText(tagName);

            //closeBtn attr
            isCloseBtn = context.obtainStyledAttributes(attrs, R.styleable.TagView).getBoolean(R.styleable.TagView_closeButton, true);
            if(!isCloseBtn) DisableCloseBtn();
        } else {
            TextView tagNameView = (TextView) findViewById(R.id.tagView_tagName);
            tagNameView.setText(tagName);

            if(!isCloseBtn) DisableCloseBtn();
        }

    }
    public void inflate(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.tag_view, this);

        setOrientation(LinearLayout.HORIZONTAL);
        setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        TextView tagNameView = (TextView) findViewById(R.id.tagView_tagName);
        tagNameView.setText(tagName);

        if(!isCloseBtn) DisableCloseBtn();

        LayoutParams params = (LinearLayout.LayoutParams) v.getLayoutParams();
        params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        v.setLayoutParams(params);

    }
    public void DisableCloseBtn() {
        ImageView closeBtn = findViewById(R.id.tagView_btn);
        closeBtn.setVisibility(GONE);
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;

        //TextView tagNameView = (TextView) findViewById(R.id.tagView_tagName);
        //tagNameView.setText(tagName);
    }
    public void setCloseBtn(boolean b) {
        this.isCloseBtn = b;
    }
    public void setMarginTop(int value) {
        // convert px to dp
        int value_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, getResources()
                        .getDisplayMetrics());
        this.marginTop = value_dp;
    }
    public void setMarginBottom(int value) {
        int value_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, getResources()
                        .getDisplayMetrics());
        this.marginBottom = value_dp;
    }
    public void setMarginRight(int value) {
        int value_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, getResources()
                        .getDisplayMetrics());
        this.marginRight = value_dp;

    }
    public void setMarginLeft(int value) {
        int value_dp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, value, getResources()
                        .getDisplayMetrics());
        this.marginLeft = value_dp;
    }
}
