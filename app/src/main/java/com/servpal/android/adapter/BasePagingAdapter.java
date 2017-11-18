package com.servpal.android.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BasePagingAdapter<T> extends RecyclerView.Adapter implements PagingAdapter<T> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingAdded;

    private List<T> list;

    BasePagingAdapter(List<T> items) {
        this.list = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

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
        // add(new Object());
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

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}
