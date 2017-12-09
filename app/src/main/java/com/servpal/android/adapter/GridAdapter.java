package com.servpal.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.servpal.android.R;
import com.servpal.android.api.ServpalHttpClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private List<String> items;

    public GridAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
        String[] gridItems = context.getResources().getStringArray(R.array.grid_categories);
        items.addAll(Arrays.asList(gridItems));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        GridItemVH holder;
        if (view != null) {
            holder = (GridItemVH) view.getTag();
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
            holder = new GridItemVH(view);
            view.setTag(holder);
        }

        String text = items.get(position);

        String imagePath = text.toLowerCase().replace(" ", "-") + ".png";
        Picasso.with(context)
                .load(ServpalHttpClient.baseUrl() + "images/services/" + imagePath)
                .into(holder.image);

        holder.text.setText(text);

        return view;
    }

    static class GridItemVH extends RecyclerView.ViewHolder {
        @BindView(R.id.grid_item_image)
        ImageView image;
        @BindView(R.id.grid_item_text)
        TextView text;

        GridItemVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
