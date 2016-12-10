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
import com.techart.smartsalon.mvp.model.appointment.CashOnDelivery;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class CashAppointmentAdapter extends RecyclerView.Adapter<CashAppointmentAdapter.MainVH> {
    private List<CashOnDelivery> mCashOnDeliveryLists;
    private Context mContext;
    private ClickItemListener mClickItemAppointmentListener;
    public CashAppointmentAdapter(Context context, ClickItemListener mClickItemAppointmentListener, @NonNull List<CashOnDelivery> cashOnDeliveryLists){
        this.mContext=context;
        this.mClickItemAppointmentListener = mClickItemAppointmentListener;
        this.mCashOnDeliveryLists=cashOnDeliveryLists;
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CashAppointmentAdapter.MainVH(LayoutInflater.from(mContext).inflate(R.layout.cash_delivery_list_item, parent, false));
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(MainVH holder, int position) {
        try {
            if (mCashOnDeliveryLists.size() > 0) {
                CashOnDelivery cashOnDelivery =mCashOnDeliveryLists.get(position);
                holder.mHeader.setText(cashOnDelivery.getName());
                holder.txt_time.setText(cashOnDelivery.getDuration() + " mins");
                holder.txt_money.setText(cashOnDelivery.getCurrency() + String.format("%.1f", Float.valueOf(cashOnDelivery.getPrice())));
                holder.itemView.setOnClickListener(v -> mClickItemAppointmentListener.clickItem(cashOnDelivery.getId(), cashOnDelivery.getName()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mCashOnDeliveryLists!=null?mCashOnDeliveryLists.size():0;
    }

    class MainVH extends RecyclerView.ViewHolder {
        TextView mHeader;
        TextView txt_time;
        TextView txt_money;
        MainVH(View itemView) {
            super(itemView);
            mHeader=(TextView)itemView.findViewById(R.id.txtHeader);
            txt_time=(TextView)itemView.findViewById(R.id.txt_time);
            txt_money=(TextView)itemView.findViewById(R.id.txt_money);
        }
    }
}