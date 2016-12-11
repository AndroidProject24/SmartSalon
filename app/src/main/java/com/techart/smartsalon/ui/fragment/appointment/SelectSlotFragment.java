package com.techart.smartsalon.ui.fragment.appointment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemListener;
import com.techart.smartsalon.libs.calendar.CompactCalendarView;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.presenter.appointment.SlotAppointmentPresenter;
import com.techart.smartsalon.mvp.view.appointment.SlotAppointmentView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.SelectSlotAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Logger;
import com.techart.smartsalon.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Toan.IT
 * Date: 16/06/2016
 */

public class SelectSlotFragment extends BaseFragment implements SlotAppointmentView, ClickItemListener {
    @BindView(R.id.recyclerview_provider)
    RecyclerView mRecyclerviewProvider;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.layout_select_provider)
    RelativeLayout mLayoutSelectProvider;
    @BindView(R.id.compactcalendar_view)
    CompactCalendarView mCompactcalendarView;
    private Context mContext;
    @Inject
    SlotAppointmentPresenter
    mSlotAppointmentPresenter;

    public static Fragment newInstance() {
        return new SelectSlotFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
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
        Utils.layoutError(mContext, mLayoutSelectProvider, "We are off today");
    }

    @Override
    public void showEmty() {
        Utils.layoutError(mContext, mLayoutSelectProvider, "We are off today");
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mSlotAppointmentPresenter.attachView(this);
        initCalendar();
        mRecyclerviewProvider.setLayoutManager(new GridLayoutManager(mContext, 5));
        mRecyclerviewProvider.setHasFixedSize(true);
        mAvloadingIndicatorView.setIndicatorColor(R.color.white);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.select_slot_fragment;
    }

    @Override
    protected void initData() {
        if (!mSlotAppointmentPresenter.getPreferencesHelper().getService_id().equalsIgnoreCase(""))
            mSlotAppointmentPresenter.selectSlot(mSlotAppointmentPresenter.getPreferencesHelper().getService_id(), mSlotAppointmentPresenter.getPreferencesHelper().getProviderId(),new Date());
        else
            mSlotAppointmentPresenter.selectAllSlot(mSlotAppointmentPresenter.getPreferencesHelper().getService_id(),new Date());
    }

    @Override
    public void clickItem(String id, String name) {
        mSlotAppointmentPresenter.getPreferencesHelper().putSlot(id);
        replaceFagment(getFragmentManager(), R.id.fragment, BookAppointmentFragment.newInstance());
    }

    @Override
    public void selectSlot(List<String> stringList) {
        SelectSlotAdapter selectSlotAdapter = new SelectSlotAdapter(mContext, this, stringList);
        mRecyclerviewProvider.setAdapter(selectSlotAdapter);
    }
    private void initCalendar(){
        //month
        Logger.e(new SimpleDateFormat("MMM - yyyy", Locale.getDefault()).format(mCompactcalendarView.getFirstDayOfCurrentMonth()));
        mCompactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Logger.e(Utils.convertDate(dateClicked));
                if (!mSlotAppointmentPresenter.getPreferencesHelper().getService_id().equalsIgnoreCase(""))
                    mSlotAppointmentPresenter.selectSlot(mSlotAppointmentPresenter.getPreferencesHelper().getService_id(), mSlotAppointmentPresenter.getPreferencesHelper().getProviderId(),dateClicked);
                else
                    mSlotAppointmentPresenter.selectAllSlot(mSlotAppointmentPresenter.getPreferencesHelper().getService_id(),dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
    }
}
