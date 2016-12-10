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

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemListener;
import com.techart.smartsalon.mvp.model.contact.Contact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private List<Contact> mListAppointmentList;
    private Context mContext;
    private ClickItemListener mClickItemAppointmentListener;

    public ContactAdapter(Context context, ClickItemListener mClickItemAppointmentListener, @NonNull List<Contact> mListAppointmentList) {
        this.mContext = context;
        this.mClickItemAppointmentListener = mClickItemAppointmentListener;
        this.mListAppointmentList = mListAppointmentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.contact_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(mListAppointmentList.size()>0) {
            Contact contact = mListAppointmentList.get(position);
            holder.mTxtName.setText(contact.getSubject());
            holder.mTxtContent.setText(contact.getContent());
            holder.itemView.setOnClickListener(v -> mClickItemAppointmentListener.clickItem(contact.getSubject(), contact.getContent()));
        }
    }

    @Override
    public int getItemCount() {
        return mListAppointmentList != null ? mListAppointmentList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
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
