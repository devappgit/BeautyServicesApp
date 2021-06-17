package com.beautyservices.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.beautyservices.android.R;
import com.beautyservices.android.customview.ToggleableViewPager;
import com.beautyservices.android.ui.fragments.DashboardFragment;
import com.beautyservices.android.ui.fragments.ProfileFragment;
import com.beautyservices.android.ui.fragments.SearchFragment;
import com.beautyservices.android.ui.fragments.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ToggleableViewPager pager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomBar;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        ButterKnife.bind(this);

        MainPagingAdapter adapter = new MainPagingAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setPagingEnabled(false);

        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.action_search:
                        pager.setCurrentItem(0, false);
                        break;
                    case R.id.action_dashboard:
                        pager.setCurrentItem(1, false);
                        break;
                    case R.id.action_profile:
                        pager.setCurrentItem(2, false);
                        break;
                    case R.id.action_settings:
                        pager.setCurrentItem(3, false);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    static class MainPagingAdapter extends FragmentPagerAdapter {

        MainPagingAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new SearchFragment();
                case 1:
                    return new DashboardFragment();
                case 2:
                    return new ProfileFragment();
                case 3:
                    return new SettingsFragment();
            }
            return new Fragment();
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
