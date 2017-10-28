package com.servpal.android.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.servpal.android.R;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.Session;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    // When MainActivity gets native content, use this intent factory
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    // until then, use the Uri intent to send to Chrome
    public static Intent newUriIntent(String phpSess) {
        Uri uri = Uri.parse(ServpalHttpClient.baseUrl() + "professionals/find?" + Uri.encode(phpSess));
        Timber.d(uri.toString());
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.provider_button)
    void onProviderClicked() {
        startActivity(new Intent(this, ProviderServicesActivity.class));
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
