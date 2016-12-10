package com.techart.smartsalon.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemContentListener;
import com.techart.smartsalon.mvp.model.offers.Offers;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {
    private List<Offers> mOffersList;
    private Context mContext;
    private ClickItemContentListener mClickItemListener;

    public OffersAdapter(Context context, ClickItemContentListener clickItemListener, @NonNull List<Offers> offersList) {
        this.mContext = context;
        this.mClickItemListener = clickItemListener;
        this.mOffersList = offersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.offers_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mOffersList.size()>0) {
            Offers offers = mOffersList.get(position);
            holder.mTxtName.setText(offers.getTitle());
            holder.mTxtContent.setText(offers.getContent());
            holder.itemView.setOnClickListener(v -> mClickItemListener.clickItem(offers.getId(), offers.getTitle(), offers.getContent(), offers.getExpiration_date()));
        }
    }

    @Override
    public int getItemCount() {
        return mOffersList != null ? mOffersList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_name)
        TextView mTxtName;
        @BindView(R.id.txt_content)
        TextView mTxtContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
