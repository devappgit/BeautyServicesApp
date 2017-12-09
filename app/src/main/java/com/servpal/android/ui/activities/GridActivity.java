package com.servpal.android.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.servpal.android.R;
import com.servpal.android.adapter.GridAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GridActivity extends AppCompatActivity {

    @BindView(R.id.grid_view)
    GridView gridView;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);

        GridAdapter adapter = new GridAdapter(this);
        gridView.setAdapter(adapter);
    }
}
