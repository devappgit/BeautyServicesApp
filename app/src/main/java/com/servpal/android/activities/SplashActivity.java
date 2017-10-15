package com.servpal.android.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // windowBackground is faster than contentView, background is at @drawable/background_splash
        // which is declared in SplashTheme and applied through AndroidManifest

        // check logged-in state

        // postDelayed to simulate network?
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LaunchActivity.class));
            }
        }, 2000);
    }
}
