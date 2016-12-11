package com.techart.smartsalon.ui.fragment.appointment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.appointment.SelectProvider;
import com.techart.smartsalon.mvp.presenter.appointment.ProviderAppointmentPresenter;
import com.techart.smartsalon.mvp.view.appointment.ProviderAppointmentView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.SelectProviderAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 15/06/2016
 */

public class SelectProviderFragment extends BaseFragment implements ProviderAppointmentView,ClickItemListener {
    @BindView(R.id.recyclerview_provider)
    RecyclerView mRecyclerviewProvider;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.layout_select_provider)
    RelativeLayout mLayoutSelectProvider;
    private Context mContext;
    @Inject
    ProviderAppointmentPresenter
    mProviderAppointmentPresenter;
    public static Fragment newInstance() {
        return new SelectProviderFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
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
        Utils.layoutError(mContext,mLayoutSelectProvider,"No provider serves this service yet");
    }

    @Override
    public void showEmty() {
        Utils.layoutError(mContext,mLayoutSelectProvider,"No provider serves this service yet");
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void initViews() {
        ((MainActivity)getActivity()).getActivityComponent().inject(this);
        mProviderAppointmentPresenter.attachView(this);
        mRecyclerviewProvider.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerviewProvider.setHasFixedSize(true);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.select_provider_fragment;
    }

    @Override
    protected void initData() {
        mProviderAppointmentPresenter.selectProvider(mProviderAppointmentPresenter.getPreferencesHelper().getService_id());
    }
    @Override
    public void selectProvice(List<SelectProvider> selectProviderList) {
        mRecyclerviewProvider.setAdapter(new SelectProviderAdapter(mContext,this,selectProviderList));
    }

    @Override
    public void clickItem(String id,String name) {
        mProviderAppointmentPresenter.getPreferencesHelper().putProviderId(id);
        mProviderAppointmentPresenter.getPreferencesHelper().putProviderName(name);
        replaceFagment(getFragmentManager(),R.id.fragment,SelectSlotFragment.newInstance());
    }
}
