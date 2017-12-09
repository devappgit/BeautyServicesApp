package com.servpal.android.ui.activities;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.jakewharton.rxbinding2.widget.RxSearchView;
import com.servpal.android.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

public abstract class AbsRecyclerActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abs_activity_recycler);

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

        SearchView searchView = findViewById(R.id.search_view);
        RxSearchView.queryTextChanges(searchView)
                .debounce(350, TimeUnit.MILLISECONDS)
                .map(charSequence -> charSequence.toString().trim())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .skip(1)    // don't send a request for the initial emission on observe
                .subscribe(this::onSearch);
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

    protected abstract void onSearch(String query);
}
