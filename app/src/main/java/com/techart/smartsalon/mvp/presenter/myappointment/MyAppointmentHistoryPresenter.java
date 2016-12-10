package com.techart.smartsalon.mvp.presenter.myappointment;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.myappointment.ListAppointment;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.myappointment.MyAppointmentHistoryView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class MyAppointmentHistoryPresenter extends BasePresenter<MyAppointmentHistoryView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    MyAppointmentHistoryPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void myAppointmentHistoryList(){
        getMvpView().showLoading();
        mRestData.getAppointmentsHistory(mPreferencesHelper.getUserId())
                .subscribe(new Subscriber<List<ListAppointment>>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getMvpView().hideLoading();
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<ListAppointment> appointmentDetailsList) {
                        try {
                            getMvpView().getmyAppointmentHistory(appointmentDetailsList);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
    public PreferencesHelper getPreferencesHelper(){
        return mPreferencesHelper;
    }
}
