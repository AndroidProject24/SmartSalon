package com.techart.smartsalon.mvp.presenter.appointment;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.appointment.BookAppointment;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.appointment.BookAppointmentView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class BookAppointmentPresenter extends BasePresenter<BookAppointmentView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    BookAppointmentPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void confirmBooking(){
        getMvpView().showLoading();
        mRestData.getBook(mPreferencesHelper.getUserId(),mPreferencesHelper.getService_id(), mPreferencesHelper.getPrepaid_id(),mPreferencesHelper.getProviderId(),
                 mPreferencesHelper.getDate(),mPreferencesHelper.getSlot(),mPreferencesHelper.getPackage_type(),mPreferencesHelper.getFistName())
                .subscribe(new Subscriber<BookAppointment>() {
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
                    public void onNext(BookAppointment bookAppointment) {
                        try {
                            if(bookAppointment!=null) {
                                getMvpView().complete();
                            }else{
                                getMvpView().showError();
                            }
                        }catch (Exception e){
                            getMvpView().showError();
                            e.printStackTrace();
                        }
                    }
                });
    }
    public PreferencesHelper getPreferencesHelper(){
        return mPreferencesHelper;
    }
}
