package com.servpal.android.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.servpal.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProviderServicesActivity extends AppCompatActivity {

    @BindView(R.id.backdrop)
    ImageView backdrop;

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    // some kind of local data
    // ProviderServicesList that has Profile and List<Services>?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_services);    // TODO: layout has hella work left
        ButterKnife.bind(this);

        // network call for provider services
        // populate image
        // populate title
        // generate Tabs
        // create fragments with ViewPager
    }

    private class ProviderServicesPagerAdapter extends FragmentStatePagerAdapter {

        public ProviderServicesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) { // fragments accept bundle args to instantiate?
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}
