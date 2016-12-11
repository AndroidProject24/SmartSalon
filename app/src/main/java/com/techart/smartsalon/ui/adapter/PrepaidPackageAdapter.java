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
import com.techart.smartsalon.interfaces.ClickItemRedeemListener;
import com.techart.smartsalon.mvp.model.appointment.PrepaidPackages;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class PrepaidPackageAdapter extends RecyclerView.Adapter<PrepaidPackageAdapter.ViewHolder> {
    private List<PrepaidPackages> mPrepaidPackagesList;
    private Context mContext;
    private ClickItemRedeemListener mClickItemAppointmentListener;

    public PrepaidPackageAdapter(Context context, ClickItemRedeemListener mClickItemAppointmentListener, @NonNull List<PrepaidPackages> prepaidPackagesList) {
        this.mContext = context;
        this.mClickItemAppointmentListener = mClickItemAppointmentListener;
        this.mPrepaidPackagesList = prepaidPackagesList;
    }

    @Override
    public PrepaidPackageAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new PrepaidPackageAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.prepaid_package_list_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final PrepaidPackageAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mPrepaidPackagesList.size()>0) {
            PrepaidPackages prepaidPackages = mPrepaidPackagesList.get(position);
            holder.mTxtName.setText(prepaidPackages.getService_name());
            holder.mTxtContent.setText(String.valueOf(Integer.parseInt(prepaidPackages.getBuy()) + Integer.parseInt(prepaidPackages.getFree()) - Integer.parseInt(prepaidPackages.getUsed())) + " services left");
            holder.itemView.setOnClickListener(v -> mClickItemAppointmentListener.clickItem(prepaidPackages.getService_id(),prepaidPackages.getPrepaid_id(), prepaidPackages.getService_name()));
        }
    }

    @Override
    public int getItemCount() {
        return mPrepaidPackagesList!=null?mPrepaidPackagesList.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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