package com.yklee.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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

    public TagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateViews(context, attrs);
    }

    public TagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateViews(context, attrs);
    }

    public void inflateViews(Context context, AttributeSet attrs) {
        // view inflate
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tag_view, this);

        if(attrs != null) {
            //tagName attr
            tagName = context.obtainStyledAttributes(attrs, R.styleable.TagView).getString(R.styleable.TagView_tagName);
            TextView tagNameView = (TextView) findViewById(R.id.tagView_tagName);
            tagNameView.setText(tagName);

            //closeBtn attr
            isCloseBtn = context.obtainStyledAttributes(attrs, R.styleable.TagView).getBoolean(R.styleable.TagView_closeButton, true);
            if(!isCloseBtn) DisableCloseBtn();
        }

    }
    public void DisableCloseBtn() {
        ImageView closeBtn = findViewById(R.id.tagView_btn);
        closeBtn.setVisibility(GONE);
    }
}
