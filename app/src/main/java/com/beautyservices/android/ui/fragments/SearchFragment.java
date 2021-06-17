package com.beautyservices.android.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.beautyservices.android.R;
import com.beautyservices.android.adapter.GridAdapter;
import com.beautyservices.android.ui.activities.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;

public class SearchFragment extends Fragment {

    private Unbinder unbinder;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
    @BindView(R.id.grid_view)
    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        //((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView.setAdapter(new GridAdapter(getContext()));

        // TODO: Set up listener on toolbar/searchview -> SearchActivity.newIntent(context)
    }

    @OnItemClick(R.id.grid_view)
    void onGridItemClicked(int position) {
        if (getContext() != null) { // get array of gridItems (same as used in GridAdapter), determine category of search with array
            String[] gridItems = getContext().getResources().getStringArray(R.array.grid_categories);
            startActivity(SearchActivity.newIntentWithSearch(getContext(), gridItems[position]));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
