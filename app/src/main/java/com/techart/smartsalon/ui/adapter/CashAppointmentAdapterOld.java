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
import com.techart.smartsalon.libs.SectionedRecyclerViewAdapter;
import com.techart.smartsalon.mvp.model.appointment.CashOnDelivery;
import com.techart.smartsalon.mvp.model.appointment.CashOnDeliveryList;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class CashAppointmentAdapterOld extends SectionedRecyclerViewAdapter<CashAppointmentAdapterOld.MainVH> {
    private List<CashOnDeliveryList> mCashOnDeliveryLists;
    private Context mContext;
    private ClickItemListener mClickItemAppointmentListener;
    public CashAppointmentAdapterOld(Context context, ClickItemListener mClickItemAppointmentListener, @NonNull List<CashOnDeliveryList> cashOnDeliveryLists){
        this.mContext=context;
        this.mClickItemAppointmentListener = mClickItemAppointmentListener;
        this.mCashOnDeliveryLists=cashOnDeliveryLists;
    }
    @Override
    public int getSectionCount() {
        return mCashOnDeliveryLists!=null?mCashOnDeliveryLists.size():0;
    }

    @Override
    public int getItemCount(int section) {
        return 0;// mCashOnDeliveryLists.get(section).getServices().size();
    }

    @Override
    public void onBindHeaderViewHolder(MainVH holder, int section) {
        //if(holder.txt_time==null)
        //holder.mHeader.setText(mCashOnDeliveryLists.get(section).getCategory_name());
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(MainVH holder, int section, int relativePosition, int absolutePosition) {
        if(mCashOnDeliveryLists.size()>0) {
            CashOnDelivery cashOnDelivery = null;//mCashOnDeliveryLists.get(section).getServices().get(relativePosition);
            holder.mHeader.setText(cashOnDelivery.getName());
            holder.txt_time.setText(cashOnDelivery.getDuration() + " mins");
            holder.txt_money.setText(cashOnDelivery.getCurrency() + String.format("%.1f", Float.valueOf(cashOnDelivery.getPrice())));
            holder.itemView.setOnClickListener(v -> mClickItemAppointmentListener.clickItem(cashOnDelivery.getId(), cashOnDelivery.getName()));
        }
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                layout = R.layout.cash_delivery_list_header;
                break;
            case VIEW_TYPE_ITEM:
                layout = R.layout.cash_delivery_list_item;
                break;
            default:
                layout = R.layout.cash_delivery_list_header;
                break;
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MainVH(v);
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