package com.techart.smartsalon.ui.fragment.myappointment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.myappointment.ListAppointment;
import com.techart.smartsalon.mvp.presenter.myappointment.MyAppointmentPresenter;
import com.techart.smartsalon.mvp.view.myappointment.MyAppointmentView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.MyAppointmentAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Utils;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class MyAppointmentFragment extends BaseFragment implements MyAppointmentView,ClickItemListener {
    @Inject
    MyAppointmentPresenter
    mMyAppointmentPresenter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.txt_date)
    TextView mTextDate;
    @BindView(R.id.txt_error)
    TextView txt_error;
    private Context mContext;

    public static Fragment newInstance() {
        return new MyAppointmentFragment();
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
        return R.layout.myappointment_fragment;
    }

    @Override
    protected void initData() {
        String date=Utils.convertDate(new Date());
        mTextDate.setText(date);
        mMyAppointmentPresenter.myAppointmentList(date);
    }

    @Override
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mMyAppointmentPresenter.attachView(this);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setHasFixedSize(true);
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
    public void clickItem(String id, String name) {
        replaceFagment(getFragmentManager(),R.id.fragment, AppointmentDetailsFragment.newInstance(id));
    }
    @Override
    public void myAppointmentList(List<ListAppointment> listAppointments) {
        MyAppointmentAdapter myAppointmentAdapter=new MyAppointmentAdapter(mContext,this,listAppointments);
        mRecyclerview.setAdapter(myAppointmentAdapter);
    }
}