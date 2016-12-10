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
import com.techart.smartsalon.mvp.model.myappointment.ListAppointment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class MyAppointmentHistoryAdapter extends RecyclerView.Adapter<MyAppointmentHistoryAdapter.ViewHolder> {
    private List<ListAppointment> mListAppointmentList;
    private ClickItemIDListener clickDetailsHistoryListener;
    private Context mContext;

    public MyAppointmentHistoryAdapter(Context context,ClickItemIDListener clickDetailsHistoryListener, @NonNull List<ListAppointment> mListAppointmentList) {
        this.mContext = context;
        this.clickDetailsHistoryListener=clickDetailsHistoryListener;
        this.mListAppointmentList = mListAppointmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.myappointment_history_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            if (mListAppointmentList.size() > 0) {
                ListAppointment appointment = mListAppointmentList.get(position);
                holder.mTxtName.setText(appointment.getServices().getName());
                holder.mTxtName1.setText(appointment.getProvider().getFirst_name() + " " + appointment.getProvider().getLast_name());
                holder.mTxtTime.setText(appointment.getDate() + " " + appointment.getSlot());
                holder.mTxtCheck.setText(appointment.getStatusText());
                holder.itemView.setOnClickListener(view -> clickDetailsHistoryListener.clickItem(appointment.getId()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mListAppointmentList != null ? mListAppointmentList.size() : 0;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView mTxtName;
        @BindView(R.id.txt_time)
        TextView mTxtTime;
        @BindView(R.id.txt_name1)
        TextView mTxtName1;
        @BindView(R.id.txt_check)
        TextView mTxtCheck;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
