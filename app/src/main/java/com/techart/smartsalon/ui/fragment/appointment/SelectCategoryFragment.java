package com.techart.smartsalon.ui.fragment.appointment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemIDListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.appointment.ListCategory;
import com.techart.smartsalon.mvp.presenter.appointment.SelectCategoryPresenter;
import com.techart.smartsalon.mvp.view.appointment.SelectCategoryView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.SelectCategoryAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT on 12/10/16.
 * Email: huynhvantoan.itc@gmail.com
 */

public class SelectCategoryFragment extends BaseFragment implements SelectCategoryView,ClickItemIDListener {
    @BindView(R.id.recyclerview_category)
    RecyclerView mRecyclerviewCategory;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.layout_select_category)
    RelativeLayout mLayoutSelectCategory;
    private Context mContext;
    @Inject
    SelectCategoryPresenter
    selectCategoryPresenter;
    public static Fragment newInstance() {
        return new SelectCategoryFragment();
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
        Utils.layoutError(mContext,mLayoutSelectCategory,"Get category error!");
    }

    @Override
    public void showEmty() {
        Utils.layoutError(mContext,mLayoutSelectCategory,"No category!");
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void initViews() {
        ((MainActivity)getActivity()).getActivityComponent().inject(this);
        selectCategoryPresenter.attachView(this);
        mRecyclerviewCategory.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerviewCategory.setHasFixedSize(true);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.select_category_fragment;
    }

    @Override
    protected void initData() {
        selectCategoryPresenter.selectCategory();
    }

    @Override
    public void selectCategory(List<ListCategory> listCategory) {
        mRecyclerviewCategory.setAdapter(new SelectCategoryAdapter(mContext,this,listCategory));
    }

    @Override
    public void clickItem(String id) {
        selectCategoryPresenter.getPreferencesHelper().putCategoryID(id);
        replaceFagment(getFragmentManager(),R.id.fragment, CashAppointmentFragment.newInstance(id));
    }
}
