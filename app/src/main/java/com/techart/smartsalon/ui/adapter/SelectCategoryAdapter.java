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
import com.techart.smartsalon.mvp.model.appointment.ListCategory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Toan.IT
 * Date: 16/06/2016
 */

public class SelectCategoryAdapter extends RecyclerView.Adapter<SelectCategoryAdapter.ViewHolder> {
    private List<ListCategory> mSelectCategoryList;
    private Context mContext;
    private ClickItemIDListener mclickIDListener;

    public SelectCategoryAdapter(Context context, ClickItemIDListener mclickIDListener, @NonNull List<ListCategory> selectProviderList) {
        this.mContext = context;
        this.mclickIDListener = mclickIDListener;
        this.mSelectCategoryList = selectProviderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.select_category_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            if (mSelectCategoryList.size() > 0) {
                ListCategory listCategory = mSelectCategoryList.get(position);
                holder.mTxtName.setText(listCategory.getCategory_name());
                holder.itemView.setOnClickListener(v -> mclickIDListener.clickItem(listCategory.getCategory_id()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mSelectCategoryList!=null?mSelectCategoryList.size():0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        TextView mTxtName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}