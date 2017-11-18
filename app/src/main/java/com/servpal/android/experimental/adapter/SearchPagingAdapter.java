package com.servpal.android.experimental.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.servpal.android.R;
import com.servpal.android.model.Professional;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchPagingAdapter extends RecyclerView.Adapter {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private boolean isLoadingAdded;

    private List<Professional> list;

    public SearchPagingAdapter() {
        list = new ArrayList<>();
    }

    public void setResults(List<Professional> results) {
        list = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch(viewType) {
            case ITEM:
                View itemView = inflater.inflate(R.layout.item_provider, parent, false);
                viewHolder = new ItemVH(itemView);
                break;
            case LOADING:
                View loadingView = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(loadingView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Professional pro = getItem(position);

        switch(getItemViewType(position)) {
            case ITEM:
                final ItemVH item = (ItemVH) holder;

                item.title.setText(pro.getBusiness());
                item.subTitle.setText(pro.getProfession());
                item.caption.setText(pro.getLocation());
                break;
            case LOADING:
                // do nothing
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == list.size() -1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public Professional getItem(int position) {
        return list.get(position);
    }

    public void add(Professional item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    public void addAll(List<Professional> items) {
        for (Professional item : items) {
            add(item);
        }
    }

    public void remove(Professional item) {
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
        add(new Professional());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = list.size() - 1;
        Professional item = getItem(position);

        if (item != null) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }


    static class ItemVH extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.sub_title)
        TextView subTitle;
        @BindView(R.id.caption)
        TextView caption;

        ItemVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class LoadingVH extends RecyclerView.ViewHolder {

        LoadingVH(View itemView) {
            super(itemView);
        }
    }
}
