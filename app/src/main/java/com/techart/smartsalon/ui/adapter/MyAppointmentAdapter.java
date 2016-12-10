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
import com.techart.smartsalon.mvp.model.myappointment.ListAppointment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class MyAppointmentAdapter extends RecyclerView.Adapter<MyAppointmentAdapter.ViewHolder> {
    private List<ListAppointment> mListAppointmentList;
    private Context mContext;
    private ClickItemListener mClickItemAppointmentListener;

    public MyAppointmentAdapter(Context context, ClickItemListener mClickItemAppointmentListener, @NonNull List<ListAppointment> mListAppointmentList) {
        this.mContext = context;
        this.mClickItemAppointmentListener = mClickItemAppointmentListener;
        this.mListAppointmentList = mListAppointmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.myappointment_list_item, parent, false));
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mListAppointmentList.size()>0) {
            ListAppointment appointment = mListAppointmentList.get(position);
            holder.mTxtTime1.setText(appointment.getSlot());
            holder.mTxtTime2.setText(appointment.getServices().getDuration() + " mins");
            holder.mTxtName1.setText(appointment.getServices().getName());
            holder.mTxtName2.setText(appointment.getProvider().getFirst_name() + " " + appointment.getProvider().getLast_name());
            holder.mTxtMoney.setText(appointment.getServices().getCurrency() + String.format("%.1f", Float.valueOf(appointment.getServices().getPrice())));
            holder.mTxtCheck.setText(appointment.getStatusText());
            holder.itemView.setOnClickListener(v -> mClickItemAppointmentListener.clickItem(appointment.getId(), appointment.getId()));
        }
    }

    @Override
    public int getItemCount() {
        return mListAppointmentList != null ? mListAppointmentList.size() : 0;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txt_time1)
        TextView mTxtTime1;
        @BindView(R.id.txt_time2)
        TextView mTxtTime2;
        @BindView(R.id.txt_name1)
        TextView mTxtName1;
        @BindView(R.id.txt_name2)
        TextView mTxtName2;
        @BindView(R.id.txt_money)
        TextView mTxtMoney;
        @BindView(R.id.txt_check)
        TextView mTxtCheck;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
