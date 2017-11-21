package com.servpal.android.ui.activities;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.servpal.android.R;

public abstract class AbsRecyclerActivity extends AppCompatActivity {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        // bind views
        recycler = findViewById(R.id.recycler);
        refreshLayout = findViewById(R.id.swipe_refresh);

        // set default layout manager
        recycler.setLayoutManager(getLayoutManager());
        //recycler.setItemAnimator(new DefaultItemAnimator()); // is this necessary?

        // attach listeners to abstract methods
        refreshLayout.setOnRefreshListener(this::onRefresh);
        // TODO: Scroll Listener for pagination
    }

    /**
     * Override {#getLayoutManager()} if you want to use something other than {@link LinearLayoutManager}
     * @return {@link RecyclerView.LayoutManager}
     */
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    protected SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    protected RecyclerView getRecycler() {
        return recycler;
    }

    protected abstract void onRefresh();

    protected abstract void onPagination();
}
