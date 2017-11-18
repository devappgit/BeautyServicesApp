package com.servpal.android.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.servpal.android.R;
import com.servpal.android.model.Professional;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultAdapter extends RecyclerView.Adapter {

    private List<Professional> list;

    public SearchResultAdapter(List<Professional> professionals) {
        this.list = professionals;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provider, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem item = (ListItem) holder;
        Professional pro = list.get(position);

        item.title.setText(pro.getBusiness());
        item.subTitle.setText(pro.getProfession());
        item.caption.setText(pro.getLocation());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ListItem extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.sub_title)
        TextView subTitle;
        @BindView(R.id.caption)
        TextView caption;

        ListItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
