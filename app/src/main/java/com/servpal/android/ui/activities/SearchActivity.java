package com.servpal.android.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.servpal.android.R;
import com.servpal.android.adapter.SearchResultAdapter;
import com.servpal.android.api.NetworkCallback;
import com.servpal.android.api.ServpalHttpClient;
import com.servpal.android.model.SearchResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.recycler) RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        recycler.setLayoutManager(new LinearLayoutManager(this));


        ServpalHttpClient.getService().findProfessionals(null)
                .enqueue(new NetworkCallback<SearchResult>() {
                    @Override
                    protected void onSuccess(SearchResult response) {
                        SearchResultAdapter adapter = new SearchResultAdapter(response.getProfessionals());
                        recycler.setAdapter(adapter);
                    }

                    @Override
                    protected void onError(Error error) {
                        Timber.e(error.getMessage());
                    }
                });
    }

}
