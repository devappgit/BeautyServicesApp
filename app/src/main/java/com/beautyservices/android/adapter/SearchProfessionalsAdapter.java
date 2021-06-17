package com.beautyservices.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.beautyservices.android.R;
import com.beautyservices.android.api.BeautyservicesHttpClient;
import com.beautyservices.android.model.Professional;
import com.beautyservices.android.ui.activities.ProviderServicesActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchProfessionalsAdapter extends BasePagingAdapter<Professional> {

    private Context context;

    public SearchProfessionalsAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provider, parent, false);
        return new ItemVH(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        Professional pro = getItem(position);

        final ItemVH item = (ItemVH) holder;

        Picasso.with(context)
                .load(BeautyservicesHttpClient.baseUrl() + pro.getAvatar())
                .noFade()
                .into(item.image);

        item.title.setText(pro.getBusiness());
        item.subTitle.setText(pro.getProfession());
        item.caption.setText(pro.getLocation());

        item.frame.setOnClickListener(view -> context.startActivity(ProviderServicesActivity.newIntent(context, pro)));
    }

    static class ItemVH extends RecyclerView.ViewHolder {
        @BindView(R.id.frame)
        FrameLayout frame;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.sub_title)
        TextView subTitle;
        @BindView(R.id.caption)
        TextView caption;
        @BindView(R.id.image)
        ImageView image;

        ItemVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
