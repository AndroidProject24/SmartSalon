package com.techart.smartsalon.mvp.presenter.myappointment;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.myappointment.ListAppointment;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.myappointment.MyAppointmentView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class MyAppointmentPresenter extends BasePresenter<MyAppointmentView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    MyAppointmentPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void myAppointmentList(String date){
        getMvpView().showLoading();
        mRestData.getMyAppointments(mPreferencesHelper.getUserId(), date)
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
                            if(appointmentDetailsList.size()>0)
                                getMvpView().myAppointmentList(appointmentDetailsList);
                            else
                                getMvpView().showEmty();
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
