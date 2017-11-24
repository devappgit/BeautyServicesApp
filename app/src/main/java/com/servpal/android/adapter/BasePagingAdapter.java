package com.servpal.android.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.servpal.android.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePagingAdapter<T> extends RecyclerView.Adapter implements PagingAdapter<T> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingAdded;

    private List<T> list;

    BasePagingAdapter() {
        list = new ArrayList<>();
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch(viewType) {
            case ITEM:
                viewHolder = onCreateItemViewHolder(parent, viewType);
                break;
            case LOADING:
                View loadingView = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(loadingView);
                break;
        }
        return viewHolder;
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(getItemViewType(position)) {
            case ITEM:
                onBindItemViewHolder(holder, position);
                break;
            case LOADING:
                onBindLoadingViewHolder(holder, position);
                break;
        }
    }

    public abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);

    private void onBindLoadingViewHolder(RecyclerView.ViewHolder holder, int position) {
        // do nothing, it's just a progressbar in the loading view holder
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == list.size() -1 && isLoadingAdded) ? LOADING : ITEM;
    }


    public T getItem(int position) {
        return list.get(position);
    }

    public void add(T item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    public void addAll(List<T> items) {
        for (T item : items) {
            add(item);
        }
    }

    public void remove(T item) {
        int position = list.indexOf(item);
        if (position > -1) {
            list.remove(item);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(getItem(list.size() - 1));   // duplicate last item as a placeholder item
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = list.size() - 1;
        T item = getItem(position);

        if (item != null) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    // another ItemVH class

    protected class LoadingVH extends RecyclerView.ViewHolder {

        LoadingVH(View itemView) {
            super(itemView);
        }
    }
}
