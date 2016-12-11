package com.techart.smartsalon.ui.fragment.appointment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemRedeemListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.appointment.PrepaidPackages;
import com.techart.smartsalon.mvp.presenter.appointment.PrepaidPackagePresenter;
import com.techart.smartsalon.mvp.view.appointment.PrepaidPackageView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.PrepaidPackageAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class PrepaidPackagesFragment extends BaseFragment implements PrepaidPackageView ,ClickItemRedeemListener {
    @Inject
    PrepaidPackagePresenter
    mPrepaidPackagePresenter;
    @BindView(R.id.recyclerview_prepaid)
    RecyclerView mRecyclerviewPrepaid;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    private Context mContext;
    public static Fragment newInstance() {
        return new PrepaidPackagesFragment();
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
        return R.layout.prepaid_package_fragment;
    }

    @Override
    protected void initData() {
        mPrepaidPackagePresenter.getPrepaidPackages();
    }

    @Override
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mPrepaidPackagePresenter.attachView(this);
        mRecyclerviewPrepaid.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerviewPrepaid.setHasFixedSize(true);
    }

    @Override
    public void getPrepaidPackages(List<PrepaidPackages> prepaidPackagesList) {
        if (prepaidPackagesList.size() > 0) {
            for (int i = prepaidPackagesList.size() - 1; i >= 0; i--) {
                PrepaidPackages prepaidPackage = prepaidPackagesList.get(i);
                if(Integer.parseInt(prepaidPackage.getBuy())+Integer.parseInt(prepaidPackage.getFree())-Integer.parseInt(prepaidPackage.getUsed())<=0) {
                    prepaidPackagesList.remove(i);
                }
            }
        }
        PrepaidPackageAdapter prepaidPackageAdapter=new PrepaidPackageAdapter(mContext,this,prepaidPackagesList);
        mRecyclerviewPrepaid.setAdapter(prepaidPackageAdapter);
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
        Utils.layoutError(mContext,mRecyclerviewPrepaid,"You don't have any prepaid package");
    }

    @Override
    public void showEmty() {

    }

    @Override
    public void clickItem(String id,String prepaid_id, String name) {
        mPrepaidPackagePresenter.getPreferencesHelper().putService_id(id);
        mPrepaidPackagePresenter.getPreferencesHelper().putPrepaid_id(prepaid_id);
        mPrepaidPackagePresenter.getPreferencesHelper().putServiceName(name);
        replaceFagment(getFragmentManager(),R.id.fragment,SelectProviderFragment.newInstance());
    }
}