package com.techart.smartsalon.ui.fragment.appointment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.appointment.CashOnDelivery;
import com.techart.smartsalon.mvp.presenter.appointment.CashAppointmentPresenter;
import com.techart.smartsalon.mvp.view.appointment.CashAppointmentView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.CashAppointmentAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Constant;
import com.techart.smartsalon.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 11/06/2016
 */

public class CashAppointmentFragment extends BaseFragment implements CashAppointmentView,ClickItemListener {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.layout_main)
    RelativeLayout mLayoutMain;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @Inject
    CashAppointmentPresenter
    mCashAppointmentPresenter;
    private Context mContext;
    private GridLayoutManager manager;
    public static Fragment newInstance(String id) {
        CashAppointmentFragment cashAppointmentFragment=new CashAppointmentFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.BundleID,id);
        cashAppointmentFragment.setArguments(bundle);
        return cashAppointmentFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.cash_delivery_fragment;
    }

    @Override
    protected void initData() {
        try {
            if (getArguments().getString(Constant.BundleID) != null)
                mCashAppointmentPresenter.cashDelivery(getArguments().getString(Constant.BundleID));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void initViews() {
        ((MainActivity)getActivity()).getActivityComponent().inject(this);
        mCashAppointmentPresenter.attachView(this);
       // manager = new GridLayoutManager(mContext,1);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setHasFixedSize(true);
    }

    @Override
    public void cashOnDelivery(List<CashOnDelivery> cashOnDeliveryList) {
        CashAppointmentAdapter cashAppointmentAdapter = new CashAppointmentAdapter(mContext,this, cashOnDeliveryList);
       // CashAppointmentAdapter.setLayoutManager(manager);
        mRecyclerview.setAdapter(cashAppointmentAdapter);
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
        Utils.layoutError(mContext, mLayoutMain, "No service available.");
    }

    @Override
    public void showEmty() {
        Utils.layoutError(mContext, mLayoutMain, "No service available.");
    }

    @Override
    public void clickItem(String id,String name) {
        mCashAppointmentPresenter.getPreferencesHelper().putService_id(id);
        mCashAppointmentPresenter.getPreferencesHelper().putServiceName(name);
        replaceFagment(getFragmentManager(),R.id.fragment,SelectProviderFragment.newInstance());
    }
}
