package com.techart.smartsalon.ui.fragment.appointment;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.presenter.appointment.BookAppointmentPresenter;
import com.techart.smartsalon.mvp.view.appointment.BookAppointmentView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.ui.fragment.MainFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Toan.IT
 * Date: 16/06/2016
 */

public class BookAppointmentFragment extends BaseFragment implements BookAppointmentView, ClickItemListener {
    @BindView(R.id.txt_service)
    TextView mTxtService;
    @BindView(R.id.txt_date)
    TextView mTxtDate;
    @BindView(R.id.txt_time)
    TextView mTxtTime;
    @BindView(R.id.txt_provider)
    TextView mTxtProvider;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;

    private Context mContext;
    @Inject
    BookAppointmentPresenter
    mBookAppointmentPresenter;

    public static Fragment newInstance() {
        return new BookAppointmentFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void clickItem(String id, String name) {

    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mBookAppointmentPresenter.attachView(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.bookappointment_fragment;
    }

    @Override
    protected void initData() {
        mTxtService.setText(mBookAppointmentPresenter.getPreferencesHelper().getServiceName());
        mTxtDate.setText(mBookAppointmentPresenter.getPreferencesHelper().getDate());
        mTxtProvider.setText(mBookAppointmentPresenter.getPreferencesHelper().getProviderName());
        mTxtTime.setText(mBookAppointmentPresenter.getPreferencesHelper().getSlot());
    }

    @Override
    public void complete() {
        Snackbar.make(mTxtService, "Send Success!", Snackbar.LENGTH_LONG).show();
        replaceFagment(getFragmentManager(),R.id.fragment, MainFragment.newInstance());
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
        Snackbar.make(mTxtService, "Send Error!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showEmty() {

    }

    @OnClick(R.id.btnConfirm)
    void btnConfirm(){
        mBookAppointmentPresenter.confirmBooking();
    }
}
