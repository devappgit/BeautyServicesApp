package com.servpal.android.ui.activities;

import android.os.Bundle;

import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.experimental.adapter.SearchPagingAdapter;
import com.servpal.android.model.SearchResult;

import java.util.ArrayList;

import retrofit2.Call;
import timber.log.Timber;

public class SearchActivity extends AbsRecyclerActivity {

    private SearchPagingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new SearchPagingAdapter();
        adapter.setResults(new ArrayList<>());
        getRecycler().setAdapter(adapter);

        loadFirstPage();
    }

    private Call<SearchResult> callProfessionalsSearch(int page, String query) {
        return ServpalHttpClient.getService().findProfessionals(page, query);
    }

    private void loadFirstPage() {
        getRefreshLayout().setRefreshing(true);
        callProfessionalsSearch(1, null)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        getRefreshLayout().setRefreshing(false);
                        adapter.addAll(response.getProfessionals());
                    }

                    @Override
                    protected void onError(Error error) {
                        getRefreshLayout().setRefreshing(false);
                        Timber.e(error.getMessage());
                    }
                });
    }

    private void refresh() {
        adapter.clear();
        callProfessionalsSearch(1, null)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        adapter.addAll(response.getProfessionals());
                        getRefreshLayout().setRefreshing(false);
                    }

                    @Override
                    protected void onError(Error error) {
                        Timber.e(error.getMessage());
                        getRefreshLayout().setRefreshing(false);
                    }
                });
    }

    @Override
    protected void onRefresh() {
        Timber.d("Refresh triggered");
        refresh();
    }

    @Override
    protected void onPagination() {
        Timber.d("Paginate triggered");
    }

}
