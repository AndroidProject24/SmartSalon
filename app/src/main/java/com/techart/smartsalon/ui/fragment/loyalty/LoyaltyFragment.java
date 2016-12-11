package com.techart.smartsalon.ui.fragment.loyalty;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemIDListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.appointment.PrepaidPackages;
import com.techart.smartsalon.mvp.model.loyalty.Loyalty;
import com.techart.smartsalon.mvp.presenter.loyalty.LoyaltyPresenter;
import com.techart.smartsalon.mvp.view.loyalty.LoyaltyView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.LoyaltyAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class LoyaltyFragment extends BaseFragment implements LoyaltyView,ClickItemIDListener {
    @Inject
    LoyaltyPresenter
    mLoyaltyPresenter;
    @BindView(R.id.txt_total)
    TextView mTxtTotal;
    @BindView(R.id.recyclerview_loyalty)
    RecyclerView mRecyclerviewLoyalty;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    private Context mContext;
    public static Fragment newInstance() {
        return new LoyaltyFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.loyalty_fragment;
    }

    @Override
    protected void initData() {
        mLoyaltyPresenter.getLoyalty();
        mLoyaltyPresenter.getPrepaidPackages();
    }

    @Override
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mLoyaltyPresenter.attachView(this);
        mRecyclerviewLoyalty.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerviewLoyalty.setHasFixedSize(true);
    }

    @Override
    public void getLoyalty(Loyalty loyalty) {
        mTxtTotal.setText(loyalty.getChecked_points().substring(loyalty.getChecked_points().indexOf("."),1));
    }

    @Override
    public void getPrepaidPackages(List<PrepaidPackages> prepaidPackagesList) {
        LoyaltyAdapter loyaltyAdapter=new LoyaltyAdapter(mContext,this,prepaidPackagesList);
        mRecyclerviewLoyalty.setAdapter(loyaltyAdapter);
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
    public void clickItem(String id) {
        replaceFagment(getFragmentManager(),R.id.fragment, LoyaltyDetailsFragment.newInstance(id));
    }
}