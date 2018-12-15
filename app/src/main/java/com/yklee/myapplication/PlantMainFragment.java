package com.yklee.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PlantMainFragment extends Fragment {
    public static PlantMainFragment newInstance() {
        return new PlantMainFragment();
    }

    ListView mListView;
    MemoListAdapter mAdapter;
    ArrayList<MemoListItem> mMemoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_plantmain, container, false);

        InitData();

        // create and set list adapter
        mListView = v.findViewById(R.id.MemoListView);
        mAdapter = new MemoListAdapter(this.getActivity(), mMemoList, R.layout.listitem_memolist);
        mListView.setAdapter(mAdapter);

        return v;
    }


    void InitData() {
        // init data (temp before db connection)
        // todo : get data from db
        mMemoList = new ArrayList<>();
        mMemoList.add(new MemoListItem("메모", "메모내용 1", "하루 전"));
        mMemoList.add(new MemoListItem("기온", "메모내용 2", "2일 전"));
        mMemoList.add(new MemoListItem("기온", "메모내용 3", "2일 전"));
        mMemoList.add(new MemoListItem("기온", "메모내용 4", "2일 전"));
        mMemoList.add(new MemoListItem("기온", "메모내용 5", "2일 전"));
        mMemoList.add(new MemoListItem("기온", "메모내용 6", "2일 전"));
        mMemoList.add(new MemoListItem("기온", "메모내용 7", "2일 전"));
        mMemoList.add(new MemoListItem("기온", "메모내용 8", "2일 전"));
        mMemoList.add(new MemoListItem("기온", "메모내용 9", "2일 전"));
        mMemoList.add(new MemoListItem("기온", "메모내용 10", "2일 전"));



    }
}
