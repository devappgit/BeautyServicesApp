package com.servpal.android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.servpal.android.R;
import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.LoginResult;
import com.servpal.android.model.Session;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText emailEditor;
    @BindView(R.id.password)
    EditText passwordEditor;

    private String cookieString;

    // TODO: If notifications are used in the app, here is where you should implement the local broadcast receiver for the device token

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        // Set up the login form.

        passwordEditor.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick(R.id.email_log_in_button)
    void onLoginClicked() {
        attemptLogin();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        String email = emailEditor.getText().toString().trim();
        String password = passwordEditor.getText().toString().trim();

        // validate here

        ServpalHttpClient.getService()
                .login(email, password, true)
                .enqueue(new NetworkCallback<LoginResult>() {
                    @Override
                    protected void onSuccess(Response response) {
                        // TODO: Disable/Remove when MainActivity gets native content
                        cookieString = response.headers().get("set-cookie");
                    }
                    @Override
                    protected void onSuccess(LoginResult response) {
                        Session.persist(response.getBody().getUser());

                        // release 1 send to CCT
                        MainActivity.openCCT(LoginActivity.this, cookieString);

                        // release 2 send to MainActivity that has native content
                        //startActivity(MainActivity.newIntent(LoginActivity.this));
                    }

                    @Override
                    protected void onError(Error error) {
                        Timber.e(error.getMessage());
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }
}

