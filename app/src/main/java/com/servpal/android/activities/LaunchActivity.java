package com.servpal.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.servpal.android.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);

        // ideally this activity just checks logged-in state and redirects to landing page or main/content page
        // isLoggedIn ? MainActivity : LandingActivity
    }

    @OnClick(R.id.login_button)
    void onLoginClicked() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.social_button)
    void onSocialClicked() {
        Timber.d("Social button is a placeholder");
    }
}
