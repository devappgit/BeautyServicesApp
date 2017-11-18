package com.servpal.android.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.servpal.android.R;
import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.Session;
import com.servpal.android.model.UserBody;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.first_name)
    EditText firstNameText;
    @BindView(R.id.last_name)
    EditText lastNameText;
    @BindView(R.id.email)
    EditText emailText;
    @BindView(R.id.password)
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.email_sign_up_button)
    void onSignUpClicked() {
        attemptSignup();
    }

    private void attemptSignup() {
        String firstName = firstNameText.getText().toString().trim();
        String lastName = lastNameText.getText().toString().trim();
        String email = emailText.getText().toString().toLowerCase().trim();
        String password = emailText.getText().toString().trim();

        ServpalHttpClient.getService()
                .createAccount("member", email, password, firstName, lastName, true)
                .enqueue(new NetworkCallback<UserBody>() {
                    @Override
                    protected void onSuccess(UserBody response) {
                        Session.persist(response.getUser());
                        startActivity(MainActivity.newIntent(SignupActivity.this));
                    }

                    @Override
                    protected void onError(Error error) {
                        Timber.e(error.getMessage());
                        Toast.makeText(SignupActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
