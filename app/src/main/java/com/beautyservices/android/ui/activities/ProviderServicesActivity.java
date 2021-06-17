package com.beautyservices.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beautyservices.android.R;
import com.beautyservices.android.api.BeautyservicesHttpClient;
import com.beautyservices.android.model.Professional;
import com.beautyservices.android.utils.TextUtils;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class ProviderServicesActivity extends AppCompatActivity {

    private static final String PRO_KEY = "PROFESSIONAL_PARCELABLE";

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
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

        // enable back button

        // load default backdrop
        Picasso.with(this)
                .load(BeautyservicesHttpClient.baseUrl() + "images/logo-white.png")
                .into(backdrop);

        // find parcelled Profession object
        if (getIntent().hasExtra(PRO_KEY)) {
            pro = Parcels.unwrap(getIntent().getParcelableExtra(PRO_KEY));

            // update UI
            Picasso.with(this)
                    .load(BeautyservicesHttpClient.baseUrl() + pro.getAvatar())
                    .into(providerAvatar);

            String fullName = pro.getFirstName() + " " +  pro.getLastName();
            providerNameText.setText(fullName);

            if (TextUtils.isEmpty(pro.getPhone())) {
                providerPhoneText.setText(pro.getMobile());
            } else {
                providerPhoneText.setText(pro.getPhone());
            }

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
