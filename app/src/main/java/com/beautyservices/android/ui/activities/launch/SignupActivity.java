package com.beautyservices.android.ui.activities.launch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.beautyservices.android.R;
import com.beautyservices.android.api.NetworkCallback;
import com.beautyservices.android.api.BeautyservicesHttpClient;
import com.beautyservices.android.model.Session;
import com.beautyservices.android.model.UserBody;
import com.beautyservices.android.ui.activities.MainActivity;

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

        BeautyservicesHttpClient.getService()
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
