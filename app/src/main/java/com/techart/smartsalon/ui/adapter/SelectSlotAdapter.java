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
import com.techart.smartsalon.interfaces.ClickItemListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 16/06/2016
 */

public class SelectSlotAdapter extends RecyclerView.Adapter<SelectSlotAdapter.ViewHolder> {
    private List<String> stringList;
    private Context mContext;
    private ClickItemListener mClickItemAppointmentListener;

    public SelectSlotAdapter(Context context, ClickItemListener mClickItemAppointmentListener, @NonNull List<String> stringList) {
        this.mContext = context;
        this.mClickItemAppointmentListener = mClickItemAppointmentListener;
        this.stringList = stringList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.select_slot_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(stringList.size()>0) {
            String date = stringList.get(position);
            holder.txtHeader.setText(date);
            holder.itemView.setOnClickListener(v -> mClickItemAppointmentListener.clickItem(date, ""));
        }
    }

    @Override
    public int getItemCount() {
        return stringList!=null?stringList.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtHeader)
        TextView txtHeader;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}