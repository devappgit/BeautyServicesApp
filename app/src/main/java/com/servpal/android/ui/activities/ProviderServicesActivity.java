package com.servpal.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.servpal.android.R;
import com.servpal.android.model.Professional;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProviderServicesActivity extends AppCompatActivity {

    private static final String PRO_KEY = "PROFESSIONAL_PARCELABLE";

    @BindView(R.id.backdrop)
    ImageView backdrop;

    // some kind of local data
    // ProviderServicesList that has Profile and List<Services>?

    private Professional professional;

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
            professional = Parcels.unwrap(getIntent().getParcelableExtra(PRO_KEY));
        }
        // network call for provider services
        // populate image
        // populate title
        // generate Tabs
        // create fragments with ViewPager
    }
}
