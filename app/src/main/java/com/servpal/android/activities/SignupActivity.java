package com.servpal.android.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.servpal.android.R;
import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.Message;
import com.servpal.android.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;
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

    private String phpSess;

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
        String email = emailText.getText().toString().trim();
        String password = emailText.getText().toString().trim();

        // should build a User here? Or expect from network

        ServpalHttpClient.getService()
                .createAccount("member", email, password, firstName, lastName, true)
                .enqueue(new NetworkCallback<Message>() {
                    @Override
                    protected void onSuccess(Response response) {
                        // TODO: Disable/Remove when MainActivity gets native content
                        phpSess = response.headers().get("set-cookie");
                    }
                    @Override
                    protected void onSuccess(Message response) {
                        // This should return a User or UserBody object so we can call Session.persist(user);

                        //startActivity(MainActivity.newUriIntent(phpSess));

                        startActivity(MainActivity.newIntent(SignupActivity.this));
                    }

                    @Override
                    protected void onError(Error error) {
                        Timber.e(error.getMessage());
                        Toast.makeText(SignupActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
