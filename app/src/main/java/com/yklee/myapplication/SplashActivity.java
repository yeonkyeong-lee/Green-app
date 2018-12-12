package com.yklee.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        try {
            Thread.sleep(3000); //로딩시간이 길어지면 삭제할것
        } catch (InterruptedException e) {

        }
    }
}
