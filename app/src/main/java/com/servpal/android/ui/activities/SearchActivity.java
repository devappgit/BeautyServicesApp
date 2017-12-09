package com.servpal.android.ui.activities;

import android.os.Bundle;

import com.servpal.android.adapter.SearchProfessionalsAdapter;
import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.SearchResult;
import com.servpal.android.utils.TextUtils;

import retrofit2.Call;
import timber.log.Timber;

public class SearchActivity extends AbsRecyclerActivity {

    public static final String INITIAL_QUERY = "INITIAL_QUERY";

    private static final int PAGE_START = 1;
    private int currentPage = PAGE_START;
    private int maxPages = PAGE_START;

    private boolean loadingMore = false;
    private String currentQuery;

    private SearchProfessionalsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new SearchProfessionalsAdapter(this);
        getRecycler().setAdapter(adapter);

        if (getIntent().hasExtra(INITIAL_QUERY)) {
            currentQuery = getIntent().getStringExtra(INITIAL_QUERY);
        }   // else creates as null

        loadFirstPage();
    }

    private Call<SearchResult> callProfessionalsSearch(int page, String query) {
        return ServpalHttpClient.getService().findProfessionals(page, query);
    }

    private void loadFirstPage() {
        getRefreshLayout().setRefreshing(true);
        callProfessionalsSearch(PAGE_START, currentQuery)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        getRefreshLayout().setRefreshing(false);
                        adapter.addAll(response.getProfessionals());

                        if (response.hasMore()) {
                            adapter.addLoadingFooter();
                        }

                        currentPage = response.getPage();
                        maxPages = response.getTotalPages();
                    }

                    @Override
                    protected void onError(Error error) {
                        getRefreshLayout().setRefreshing(false);
                        Timber.e(error.getMessage());
                    }
                });
    }

    private void loadFromSearch(String query) {
        getRefreshLayout().setRefreshing(true);
        adapter.clear();
        callProfessionalsSearch(PAGE_START, query)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        if (!currentQuery.equals(response.getSearch())) {
                            return; // don't do anything if async responses don't match
                        }

                        getRefreshLayout().setRefreshing(false);
                        adapter.addAll(response.getProfessionals());

                        if (response.hasMore()) {
                            adapter.addLoadingFooter();
                        }

                        currentPage = response.getPage();
                        maxPages = response.getTotalPages();
                    }

                    @Override
                    protected void onError(Error error) {
                        getRefreshLayout().setRefreshing(false);
                        Timber.e(error.getMessage());
                    }
                });
    }

    private void loadNextPage() {
        loadingMore = true;
        callProfessionalsSearch(currentPage + 1, currentQuery)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        adapter.removeLoadingFooter();
                        adapter.addAll(response.getProfessionals());

                        if (response.hasMore()) {
                            adapter.addLoadingFooter();
                        }

                        currentPage = response.getPage();
                        maxPages = response.getTotalPages();
                        loadingMore = false;
                    }

                    @Override
                    protected void onError(Error error) {
                        adapter.removeLoadingFooter();
                        Timber.e(error.getMessage());
                    }
                });
    }

    private void refresh() {
        adapter.clear();
        callProfessionalsSearch(PAGE_START, currentQuery)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        adapter.addAll(response.getProfessionals());
                        getRefreshLayout().setRefreshing(false);

                        if (hasMore()) {
                            adapter.addLoadingFooter();
                        }

                        currentPage = response.getPage();
                        maxPages = response.getTotalPages();
                    }

                    @Override
                    protected void onError(Error error) {
                        Timber.e(error.getMessage());
                        getRefreshLayout().setRefreshing(false);
                    }
                });
    }

    @Override
    protected boolean isLoading() {
        return loadingMore;
    }

    @Override
    protected boolean hasMore() {
        return currentPage < maxPages;
    }

    @Override
    protected void onRefresh() {
        Timber.d("Refresh triggered");
        refresh();
    }

    @Override
    protected void onPagination() {
        Timber.d("Paginate triggered");
        loadNextPage();
    }

    @Override
    protected void onSearch(String query) {
        if (TextUtils.isEmpty(query)) {
            Timber.d("Search is EMPTY");
            currentQuery = null;
            loadFirstPage();
        } else {
            Timber.d("Search triggered: %s", query);
            currentQuery = query;
            loadFromSearch(query);
        }
    }
}
