package com.beautyservices.android.ui.activities.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.beautyservices.android.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity displays the App Logo and Login/Signup buttons
 */
public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    void onLoginClicked() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.signup_button)
    void onSignupClicked() {
        startActivity(new Intent(this, SignupActivity.class));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
