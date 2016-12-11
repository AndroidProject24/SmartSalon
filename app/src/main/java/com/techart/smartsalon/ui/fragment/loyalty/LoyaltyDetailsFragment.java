package com.techart.smartsalon.ui.fragment.loyalty;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.techart.smartsalon.R;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.loyalty.PrepaidAppointment;
import com.techart.smartsalon.mvp.presenter.loyalty.LoyaltyDetailsPresenter;
import com.techart.smartsalon.mvp.view.loyalty.LoyaltyDetailsView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.LoyaltyDetailsAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Constant;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class LoyaltyDetailsFragment extends BaseFragment implements LoyaltyDetailsView {
    @Inject
    LoyaltyDetailsPresenter
    mLoyaltyDetailsPresenter;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.recycle_loyalty_details)
    RecyclerView mRecycleLoyaltyDetails;
    private Context mContext;

    public static Fragment newInstance(String id) {
        LoyaltyDetailsFragment loyaltyDetailsFragment = new LoyaltyDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.BundleID, id);
        loyaltyDetailsFragment.setArguments(bundle);
        return loyaltyDetailsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.loyalty_details_fragment;
    }

    @Override
    protected void initData() {
        mLoyaltyDetailsPresenter.getLoyaltyDetails(getArguments().getString(Constant.BundleID));
    }

    @Override
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mLoyaltyDetailsPresenter.attachView(this);
        mRecycleLoyaltyDetails.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycleLoyaltyDetails.setHasFixedSize(true);
    }

    @Override
    public void showLoading() {
        if(mAvloadingIndicatorView!=null)
        mAvloadingIndicatorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if(mAvloadingIndicatorView!=null)
        mAvloadingIndicatorView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmty() {

    }

    @Override
    public void getLoyaltyDetails(List<PrepaidAppointment> prepaidAppointmentList) {
        LoyaltyDetailsAdapter loyaltyDetailsAdapter = new LoyaltyDetailsAdapter(mContext, prepaidAppointmentList);
        mRecycleLoyaltyDetails.setAdapter(loyaltyDetailsAdapter);
    }
}