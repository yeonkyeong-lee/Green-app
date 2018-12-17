package com.yklee.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MemoListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<MemoItem> data;

    public MemoListAdapter(Context context, ArrayList<MemoItem> data, int layout) {
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public MemoItem getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = inflater.inflate(R.layout.listitem_memolist, viewGroup, false);
        }

        // set MemoListView data
        TextView tagView = (TextView) view.findViewById(R.id.memoList_tagName);
        TextView contentView = (TextView) view.findViewById(R.id.memoList_contents);
        TextView dateView = (TextView) view.findViewById(R.id.memoList_date);

        MemoItem listItem = data.get(i);

        tagView.setText(listItem.getTagName().replace("tag_", ""));
        contentView.setText(listItem.getContent());
        dateView.setText(listItem.getStringDate());

        return view;
    }
}
