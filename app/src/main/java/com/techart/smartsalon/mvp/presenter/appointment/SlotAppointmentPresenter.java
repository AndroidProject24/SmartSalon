package com.techart.smartsalon.mvp.presenter.appointment;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.appointment.SlotAppointmentView;
import com.techart.smartsalon.utils.Constant;
import com.techart.smartsalon.utils.Utils;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class SlotAppointmentPresenter extends BasePresenter<SlotAppointmentView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    SlotAppointmentPresenter(RestData restData,PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void selectSlot(String service_id,String provider_id,Date date){
        getMvpView().showLoading();
        mRestData.getSlot(service_id,provider_id, Utils.convertDate(date))
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                        mPreferencesHelper.putDate(Utils.convertDate(date));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getMvpView().hideLoading();
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<String> stringList) {
                        try {
                            getMvpView().selectSlot(stringList);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void selectAllSlot(String service_id,Date date){
        getMvpView().showLoading();
        mRestData.getAllSlot(service_id, Constant.SHOP_ID,Utils.convertDate(date))
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                        mPreferencesHelper.putDate(Utils.convertDate(date));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getMvpView().hideLoading();
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<String> stringList) {
                        try {
                            getMvpView().selectSlot(stringList);
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
