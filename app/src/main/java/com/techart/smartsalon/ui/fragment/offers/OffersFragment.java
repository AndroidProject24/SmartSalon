package com.techart.smartsalon.ui.fragment.offers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemContentListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.offers.Offers;
import com.techart.smartsalon.mvp.presenter.offers.OffersPresenter;
import com.techart.smartsalon.mvp.view.offers.OffersView;
import com.techart.smartsalon.ui.activity.BaseActivity;
import com.techart.smartsalon.ui.adapter.OffersAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class OffersFragment extends BaseFragment implements OffersView,ClickItemContentListener{
    @BindView(R.id.recyclerview_offers)
    RecyclerView mRecyclerviewOffers;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.txt_error)
    TextView txt_error;
    private Context mContext;
    @Inject
    OffersPresenter
    mOffersPresenter;

    public static Fragment newInstance() {
        return new OffersFragment();
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
        return R.layout.offers_fragment;
    }

    @Override
    protected void initData() {
        mOffersPresenter.getOffers();
    }

    @Override
    protected void initViews() {
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
        mOffersPresenter.attachView(this);
        mRecyclerviewOffers.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerviewOffers.setHasFixedSize(true);
    }

    @Override
    public void getOffers(List<Offers> offersList) {
        OffersAdapter offersAdapter=new OffersAdapter(mContext,this,offersList);
        mRecyclerviewOffers.setAdapter(offersAdapter);
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
        txt_error.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmty() {
        txt_error.setVisibility(View.VISIBLE);
    }

    @Override
    public void clickItem(String id, String title, String content,String date) {
        replaceFagment(getFragmentManager(),R.id.fragment,OffersDetailsFragment.newInstance(id,title,content,date));
    }
}