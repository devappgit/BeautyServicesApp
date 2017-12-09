package com.servpal.android.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.servpal.android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomBarActivity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        ButterKnife.bind(this);

        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.action_search:
                        break;
                    case R.id.action_dashboard:
                        break;
                    case R.id.action_profile:
                        break;
                    case R.id.action_settings:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
