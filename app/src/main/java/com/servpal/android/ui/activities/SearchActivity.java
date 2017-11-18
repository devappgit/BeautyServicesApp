package com.servpal.android.ui.activities;

import android.os.Bundle;

import com.servpal.android.adapter.SearchResultAdapter;
import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.Professional;
import com.servpal.android.model.SearchResult;

import java.util.Collections;

import retrofit2.Call;
import timber.log.Timber;

public class SearchActivity extends AbsRecyclerActivity {

    private SearchResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new SearchResultAdapter(Collections.emptyList());
        getRecycler().setAdapter(adapter);

        callProfessionalsSearch(null)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        adapter = new SearchResultAdapter(response.getProfessionals());
                        getRecycler().setAdapter(adapter);
                    }

                    @Override
                    protected void onError(Error error) {
                        Timber.e(error.getMessage());
                    }
                });
    }

    private Call<SearchResult> callProfessionalsSearch(String query) {
        return ServpalHttpClient.getService().findProfessionals(query);
    }

    @Override
    protected void onRefresh() {
        Timber.d("Refresh triggered");
        getRefreshLayout().setRefreshing(false);
    }

    @Override
    protected void onPagination() {
        Timber.d("Paginate triggered");
    }

}
