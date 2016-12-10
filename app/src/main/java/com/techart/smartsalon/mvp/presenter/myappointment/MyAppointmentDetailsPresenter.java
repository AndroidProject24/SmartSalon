package com.techart.smartsalon.mvp.presenter.myappointment;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.myappointment.AppointmentDetails;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.myappointment.MyAppointmentDetailsView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class MyAppointmentDetailsPresenter extends BasePresenter<MyAppointmentDetailsView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    MyAppointmentDetailsPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void myAppointmentList(String appointmentId){
        getMvpView().showLoading();
        mRestData.getMyAppointmentDetails(appointmentId)
                .subscribe(new Subscriber<AppointmentDetails>() {
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
                    public void onNext(AppointmentDetails appointmentDetails) {
                        try {
                            getMvpView().myAppointmentDetails(appointmentDetails);
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
