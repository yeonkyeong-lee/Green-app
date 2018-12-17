package com.yklee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

/*
* 스와이프와 하단 내비게이션을 함께 사용하기 위해 fragment와 viewpager사용
* */

public class PlantPagerActivity extends FragmentActivity {
    int selectedPlant;

    int MAX_PAGE = 2;

    BottomNavigationView mBottomNavigationView;
    ViewPager mViewPager;
    PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantpager);

        // get selected plant index from intent
        Intent intent = new Intent(this.getIntent());
        selectedPlant = intent.getIntExtra("selected item", 0);
//        Log.d("selected item ", selectedPlant + "");

        //find views
        mBottomNavigationView = findViewById(R.id.bottomNavView);

        // set list adapter
        mViewPager = findViewById(R.id.plantViewPager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        // click events
        // bottom nav click event
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                ControlNavigation(item);
                return false;
            }
        });

        // 스와이프할때 하단 내비게이션 메뉴가 대응하게
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                mBottomNavigationView.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    void ControlNavigation(MenuItem item) {
        // 내비게이션 선택 메뉴에 따라 fragment변경
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.bottomNavBtn_plant :
                mViewPager.setCurrentItem(0);
                break;
            case R.id.bottomNavBtn_calender :
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    // pageAdpater class for ViewPager
    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // instantiate corresponding fragment
            switch (position) {
                case 0 :
                    PlantMainFragment frag = new PlantMainFragment();
                    Bundle bundle = new Bundle(1);
                    bundle.putInt("selected item", selectedPlant);
                    frag.setArguments(bundle);
                    return frag;
                case 1 :
                    return CalenderFragment.newInstance();

                default: return PlantMainFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }
}
