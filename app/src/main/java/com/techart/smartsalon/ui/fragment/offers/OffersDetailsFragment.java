package com.techart.smartsalon.ui.fragment.offers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.offers.OffersDetails;
import com.techart.smartsalon.mvp.presenter.offers.OffersDetailsPresenter;
import com.techart.smartsalon.mvp.view.offers.OffersDetailsView;
import com.techart.smartsalon.ui.activity.BaseActivity;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Constant;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class OffersDetailsFragment extends BaseFragment implements OffersDetailsView {
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.txt_title)
    TextView mTxtTitle;
    @BindView(R.id.txt_date)
    TextView mTxtDate;
    @BindView(R.id.txt_content)
    TextView mTxtContent;
    @Inject
    OffersDetailsPresenter
    mOffersDetailsPresenter;

    public static Fragment newInstance(String id,String title,String content,String date) {
        OffersDetailsFragment offersDetailsFragment=new OffersDetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.BundleID,id);
        bundle.putString(Constant.BundleTITLE,title);
        bundle.putString(Constant.BundleCONTENT,content);
        bundle.putString(Constant.BundleDATE,date);
        offersDetailsFragment.setArguments(bundle);
        return offersDetailsFragment;
    }
    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.offers_details_fragment;
    }

    @Override
    protected void initData() {
        mOffersDetailsPresenter.getOffersDetails(getBundle(Constant.BundleID));
    }

    @Override
    protected void initViews() {
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
        mOffersDetailsPresenter.attachView(this);
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
    public void getOffersDetails(OffersDetails offersDetails) {
        mTxtTitle.setText(getBundle(Constant.BundleTITLE));
        mTxtContent.setText(getBundle(Constant.BundleCONTENT));
        mTxtDate.setText(getBundle(Constant.BundleDATE));
    }
    private String getBundle(String bundle){
        return getArguments().getString(bundle);
    }
}