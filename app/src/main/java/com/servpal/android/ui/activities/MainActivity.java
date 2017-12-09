package com.servpal.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.servpal.android.R;
import com.servpal.android.model.Session;
import com.servpal.android.ui.activities.launch.LaunchActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.grid_button)
    void onGridClicked() {
        startActivity(new Intent(this, GridActivity.class));
    }

    @OnClick(R.id.provider_button)
    void onProviderClicked() {
        //startActivity(new Intent(this, ProviderServicesActivity.class));
        startActivity(new Intent(this, SearchActivity.class));
    }

    @OnClick(R.id.logout_button)
    void onLogoutClicked() {
        Session.clear();
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
