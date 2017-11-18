package com.servpal.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.servpal.android.R;
import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.SearchResult;
import com.servpal.android.model.Session;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

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

        ServpalHttpClient.getService().findProfessionals(null)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        Timber.d("Professionals list size: %s", response.getProfessionals().size());
                    }

                    @Override
                    protected void onError(Error error) {
                        Timber.e(error.getMessage());
                    }
                });
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
