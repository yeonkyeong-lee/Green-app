package com.yklee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class PlantMainFragment extends Fragment {
    public static PlantMainFragment newInstance() {
        return new PlantMainFragment();
    }

    int selectedPlant;

    ListView mListView;
    MemoListAdapter mAdapter;
    PlantItem mPlantItem;

    TextView nameView;
    TextView bNameView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedPlant = getArguments().getInt("selected item");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_plantmain, container, false);

        // find views
        mListView = v.findViewById(R.id.MemoListView);
        ImageView navIcon = v.findViewById(R.id.plantPage_navIcon);
        FloatingActionButton fab = v.findViewById(R.id.plantPage_FAB);
        ImageView profile = v.findViewById(R.id.plantPage_profile);
        TextView nameView = v.findViewById(R.id.plantPage_Name);
        TextView bNameView = v.findViewById(R.id.plantPage_bName);
        TextView avgCycleView = v.findViewById(R.id.plantPage_cycleText);

        nameView.setText(firebaseHandler.PlantList.get(selectedPlant).getName());
        bNameView.setText(firebaseHandler.PlantList.get(selectedPlant).getBotanicalName());
        String avgCycleText = firebaseHandler.PlantList.get(selectedPlant).getAverageCycle() + "일";
        avgCycleView.setText(avgCycleText);

        // create and set list adapter
//        mAdapter = new MemoListAdapter(this.getActivity(), mPlantItem.getMemos(), R.layout.listitem_memolist);
//        mListView.setAdapter(mAdapter);

        // click events
        navIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity_toList();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity_toAddMemo();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeActivity_toEditPlant();
            }
        });

        return v;
    }


    void InitData() {
       // get data from firebaseHandler

    }

    void ChangeActivity_toList() {
        Intent intent = new Intent(this.getActivity(), PlantListActivity.class);
        startActivity(intent);
    }
    void ChangeActivity_toAddMemo() {
        Intent intent = new Intent(this.getActivity(), AddMemoActivity.class);
        startActivity(intent);
    }
    void ChangeActivity_toEditPlant() {
        Intent intent = new Intent(this.getActivity(), EditPlantActivity.class);
        startActivity(intent);
    }
}
