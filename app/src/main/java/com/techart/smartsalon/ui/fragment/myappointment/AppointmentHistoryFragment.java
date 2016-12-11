package com.techart.smartsalon.ui.fragment.myappointment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemIDListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.myappointment.ListAppointment;
import com.techart.smartsalon.mvp.presenter.myappointment.MyAppointmentHistoryPresenter;
import com.techart.smartsalon.mvp.view.myappointment.MyAppointmentHistoryView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.MyAppointmentHistoryAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 19/06/2016
 */

public class AppointmentHistoryFragment extends BaseFragment implements MyAppointmentHistoryView,ClickItemIDListener {
    @Inject
    MyAppointmentHistoryPresenter
    mAppointmentHistoryPresenter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    private Context mContext;

    public static Fragment newInstance() {
        return new AppointmentHistoryFragment();
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
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mAppointmentHistoryPresenter.attachView(this);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setHasFixedSize(true);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.myappointment_history_fragment;
    }

    @Override
    protected void initData() {
        mAppointmentHistoryPresenter.myAppointmentHistoryList();
    }

    @Override
    public void getmyAppointmentHistory(List<ListAppointment> listAppointments) {
        MyAppointmentHistoryAdapter appointmentHistoryAdapter=new MyAppointmentHistoryAdapter(mContext,this,listAppointments);
        mRecyclerview.setAdapter(appointmentHistoryAdapter);
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
        replaceFagment(getFragmentManager(),R.id.fragment, AppointmentHistoryDetailsFragment.newInstance(id));
    }
}
