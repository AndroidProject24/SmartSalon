package com.techart.smartsalon.ui.fragment.myappointment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.myappointment.AppointmentDetails;
import com.techart.smartsalon.mvp.presenter.myappointment.MyAppointmentDetailsPresenter;
import com.techart.smartsalon.mvp.view.myappointment.MyAppointmentDetailsView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Constant;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 16/06/2016
 */

public class AppointmentDetailsFragment extends BaseFragment implements MyAppointmentDetailsView{
    @BindView(R.id.txt_service)
    TextView mTxtService;
    @BindView(R.id.txt_date)
    TextView mTxtDate;
    @BindView(R.id.txt_time)
    TextView mTxtTime;
    @BindView(R.id.txt_provider)
    TextView mTxtProvider;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAVLoadingIndicatorView;
    @Inject
    MyAppointmentDetailsPresenter
    mMyAppointmentDetailsPresenter;
    private Context mContext;
    public static Fragment newInstance(String id) {
        AppointmentDetailsFragment appointmentDetailsFragment=new AppointmentDetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putString(Constant.BundleID,id);
        appointmentDetailsFragment.setArguments(bundle);
        return appointmentDetailsFragment;
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
        mMyAppointmentDetailsPresenter.attachView(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.appointmentdetails_fragment;
    }

    @Override
    protected void initData() {
        if(getArguments().getString(Constant.BundleID)!=null)
          mMyAppointmentDetailsPresenter.myAppointmentList(getArguments().getString(Constant.BundleID));
    }

    @Override
    public void myAppointmentDetails(AppointmentDetails appointmentDetailsList) {
        mTxtService.setText(appointmentDetailsList.getServices().getName());
        mTxtDate.setText(appointmentDetailsList.getDate());
        mTxtProvider.setText(appointmentDetailsList.getProvider().getFirst_name()+" "+appointmentDetailsList.getProvider().getLast_name());
        mTxtTime.setText(appointmentDetailsList.getSlot());
    }

    @Override
    public void showLoading() {
        if(mAVLoadingIndicatorView!=null)
        mAVLoadingIndicatorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if(mAVLoadingIndicatorView!=null)
        mAVLoadingIndicatorView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmty() {

    }
}
