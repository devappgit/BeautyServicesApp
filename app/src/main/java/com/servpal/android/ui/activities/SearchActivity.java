package com.servpal.android.ui.activities;

import android.os.Bundle;

import com.servpal.android.adapter.PagingAdapterImpl;
import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.SearchResult;

import retrofit2.Call;
import timber.log.Timber;

public class SearchActivity extends AbsRecyclerActivity {

    private static final int PAGE_START = 1;

    private int currentPage = PAGE_START;
    private int maxPages = PAGE_START;

    private boolean loadingMore = false;

    private PagingAdapterImpl adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new PagingAdapterImpl(this);
        getRecycler().setAdapter(adapter);

        loadFirstPage();
    }

    private Call<SearchResult> callProfessionalsSearch(int page, String query) {
        return ServpalHttpClient.getService().findProfessionals(page, query);
    }

    private void loadFirstPage() {
        getRefreshLayout().setRefreshing(true);
        callProfessionalsSearch(PAGE_START, null)
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

    private void loadNextPage() {
        loadingMore = true;
        callProfessionalsSearch(currentPage + 1, null)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        adapter.addAll(response.getProfessionals());
                        adapter.removeLoadingFooter();

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
        callProfessionalsSearch(PAGE_START, null)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        adapter.addAll(response.getProfessionals());
                        getRefreshLayout().setRefreshing(false);

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
        return true;
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
}
