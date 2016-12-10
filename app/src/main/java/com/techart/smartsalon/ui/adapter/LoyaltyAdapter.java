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
import com.techart.smartsalon.interfaces.ClickItemIDListener;
import com.techart.smartsalon.mvp.model.appointment.PrepaidPackages;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class LoyaltyAdapter extends RecyclerView.Adapter<LoyaltyAdapter.ViewHolder> {
    private List<PrepaidPackages> mPrepaidPackagesList;
    private Context mContext;
    private ClickItemIDListener mClickItemListener;

    public LoyaltyAdapter(Context context, ClickItemIDListener clickItemListener, @NonNull List<PrepaidPackages> packagesList) {
        this.mContext = context;
        this.mClickItemListener = clickItemListener;
        this.mPrepaidPackagesList = packagesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.loyalty_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mPrepaidPackagesList.size()>0) {
            PrepaidPackages prepaidPackages = mPrepaidPackagesList.get(position);
            holder.mTxtName.setText(prepaidPackages.getService_name());
            holder.mTxtContent.setText(prepaidPackages.getBuy());
            holder.itemView.setOnClickListener(v -> mClickItemListener.clickItem(prepaidPackages.getPrepaid_id()));
        }
    }

    @Override
    public int getItemCount() {
        return mPrepaidPackagesList != null ? mPrepaidPackagesList.size() : 0;
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
