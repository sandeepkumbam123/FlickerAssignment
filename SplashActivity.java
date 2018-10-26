package com.skumbam.flickerassignmet;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.skumbam.flickerassignmet.databinding.ActivitySplashBinding;

/**
 * Created by skumbam on 10/25/18.
 */

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_TIME = 3000;
    ActivitySplashBinding dataBinding;
    private Runnable mRunnable;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_splash);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,DashBoardActivity.class));
                finish();
            }
        };

        new Handler().postDelayed(mRunnable,SPLASH_TIME);
    }
}
