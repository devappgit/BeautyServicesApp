package com.servpal.android.activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.servpal.android.R;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.Session;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    // When MainActivity gets native content, use this intent factory
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    // until then, use the Uri intent to send to Chrome
    public static Intent newUriIntent(String phpSess) {
        Uri uri = Uri.parse(ServpalHttpClient.baseUrl() + "professionals/find?" + phpSess); // TODO: need Uri.encode or no?
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    // Trying a CCT with some customization
    public static void openCCT(Context context, String phpSess) {
        PendingIntent logoutIntent =
                PendingIntent.getActivity(context, 0,
                        MainActivity.newIntentForLogout(context),
                        PendingIntent.FLAG_ONE_SHOT);

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .addMenuItem("Log out", logoutIntent)
                .setShowTitle(true);

        Uri uri = Uri.parse(ServpalHttpClient.baseUrl() + "professionals/find?" + phpSess); // TODO: need Uri.encode or no?
        builder.build().launchUrl(context, uri);
    }

    public static Intent newIntentForLogout(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("logout", true);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        boolean shouldLogOut = getIntent().getBooleanExtra("logout", false);
        if (shouldLogOut) {
            onLogoutClicked();
        }
        // otherwise normal
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
