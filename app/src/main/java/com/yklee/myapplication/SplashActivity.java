package com.yklee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

public class SplashActivity extends Activity {
    // splash timer
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                firebaseHandler.getAllData();
                Intent intent = new Intent(SplashActivity.this, PlantListActivity.class);
                startActivity(intent);
            }
        }, SPLASH_TIME_OUT);
    }
}
