package com.servpal.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.servpal.android.R;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.Professional;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProviderServicesActivity extends AppCompatActivity {

    private static final String PRO_KEY = "PROFESSIONAL_PARCELABLE";

    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.provider_avatar)
    ImageView providerAvatar;
    @BindView(R.id.provider_name)
    TextView providerNameText;
    @BindView(R.id.provider_phone)
    TextView providerPhoneText;
    @BindView(R.id.provider_about_text)
    TextView providerAboutText;
    @BindView(R.id.provider_address_text)
    TextView providerAddressText;

    // some kind of local data
    // ProviderServicesList that has Profile and List<Services>?

    private Professional pro;

    public static Intent newIntent(Context context, Professional pro) {
        Intent intent = new Intent(context, ProviderServicesActivity.class);
        intent.putExtra(PRO_KEY, Parcels.wrap(pro));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_services);
        ButterKnife.bind(this);

        if (getIntent().hasExtra(PRO_KEY)) {
            pro = Parcels.unwrap(getIntent().getParcelableExtra(PRO_KEY));

            Picasso.with(this)
                    .load(ServpalHttpClient.baseUrl() + pro.getAvatar())
                    .into(providerAvatar);

            // set texts
            String fullName = pro.getFirstName() + " " +  pro.getLastName();
            providerNameText.setText(fullName);

            String mockPhone = "(425) 891-3141";
            providerPhoneText.setText(mockPhone);

            providerAboutText.setText(pro.getDescription());

            providerAddressText.setText(pro.getLocation());
        }
        // network call for provider services
        // populate image
        // populate title
        // generate Tabs
        // create fragments with ViewPager
    }
}
