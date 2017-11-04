package com.servpal.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.servpal.android.R;
import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.Session;
import com.servpal.android.model.UserBody;

import retrofit2.Response;
import timber.log.Timber;

public class SplashActivity extends AppCompatActivity {

    private String cookieString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // check logged-in state
        if (Session.user() != null) {
            ServpalHttpClient.getService()
                    .getUser(Session.user().getId())
                    .enqueue(new NetworkCallback<UserBody>() {
                        @Override
                        protected void onSuccess(Response response) {
                            // TODO: Disable/Remove when MainActivity gets native content
                            cookieString = response.headers().get("set-cookie");
                        }
                        @Override
                        protected void onSuccess(UserBody response) {
                            Session.persist(response.getUser());
                            // release 1 send to CCT
                            MainActivity.openCCT(SplashActivity.this, cookieString);

                            // release 2 send to MainActivity that has native content
                            //startActivity(MainActivity.newIntent(SplashActivity.this));
                        }

                        @Override
                        protected void onError(Error error) {
                            Timber.e(error.getMessage());
                            Toast.makeText(SplashActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SplashActivity.this, LaunchActivity.class));
                        }
                    });
        } else {
            Timber.w("User not logged in");
            startActivity(new Intent(this, LaunchActivity.class));
        }
    }
}
