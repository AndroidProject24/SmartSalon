package com.techart.smartsalon.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemListener;
import com.techart.smartsalon.mvp.model.appointment.SelectProvider;
import com.techart.smartsalon.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 16/06/2016
 */

public class SelectProviderAdapter extends RecyclerView.Adapter<SelectProviderAdapter.ViewHolder> {
    private List<SelectProvider> mSelectProviderList;
    private Context mContext;
    private ClickItemListener mClickItemAppointmentListener;

    public SelectProviderAdapter(Context context, ClickItemListener mClickItemAppointmentListener, @NonNull List<SelectProvider> selectProviderList) {
        this.mContext = context;
        this.mClickItemAppointmentListener = mClickItemAppointmentListener;
        this.mSelectProviderList = selectProviderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.select_provider_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mSelectProviderList.size()>0) {
            SelectProvider selectProvider = mSelectProviderList.get(position);
            holder.mTxtName.setText(selectProvider.getFirst_name() +" "+ selectProvider.getLast_name());
            holder.mTxtContent.setText(selectProvider.getPhone());
            Glide.with(mContext)
                    .load(Constant.URL_IMAGE + selectProvider.getAvatar())
                    .crossFade()
                    .placeholder(R.drawable.image_loader)
                    .override(60, 60)
                    .into(holder.mImgAvatar);
            holder.itemView.setOnClickListener(v -> mClickItemAppointmentListener.clickItem(selectProvider.getId(), selectProvider.getFirst_name()));
        }
    }

    @Override
    public int getItemCount() {
        return mSelectProviderList!=null?mSelectProviderList.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_avatar)
        ImageView mImgAvatar;
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