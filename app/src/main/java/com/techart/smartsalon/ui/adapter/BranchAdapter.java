package com.techart.smartsalon.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemLoginListener;
import com.techart.smartsalon.mvp.model.login.Branch;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.internal.Preconditions;

/**
 * Created by Toan.IT
 * Date: 29/11/2016
 */

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.ViewHolder> {
    private List<Branch> mBranchList;
    private Context mContext;
    private ClickItemLoginListener mClickItemListener;
    private AlertDialog mDialog;
    public BranchAdapter(Context context, ClickItemLoginListener clickItemListener, @NonNull List<Branch> branchList,AlertDialog dialog) {
      this.mContext = context;
      this.mClickItemListener = clickItemListener;
      this.mBranchList = Preconditions.checkNotNull(branchList);
      this.mDialog=dialog;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.branch_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            if (mBranchList.size() > 0) {
                Branch branch = mBranchList.get(position);
                holder.mTxtName.setText(branch.getName());
                holder.mTxtContent.setText(branch.getAddress());
                holder.itemView.setOnClickListener(
                    v -> {
                      mClickItemListener.clickItem(branch.getId(), branch.getName());
                      mDialog.dismiss();
                    });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mBranchList != null ? mBranchList.size() : 0;
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
