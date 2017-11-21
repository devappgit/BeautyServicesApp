package com.servpal.android.ui.activities;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.servpal.android.R;

public abstract class AbsRecyclerActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;
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
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        // attach listeners to abstract methods
        refreshLayout.setOnRefreshListener(this::onRefresh);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading() && hasMore()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        onPagination();
                    }
                }
            }
        });
    }

    protected SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    protected RecyclerView getRecycler() {
        return recycler;
    }

    protected abstract boolean isLoading();

    protected abstract boolean hasMore();

    protected abstract void onRefresh();

    protected abstract void onPagination();
}
