package com.techart.smartsalon.mvp.presenter.appointment;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.appointment.SelectProvider;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.appointment.ProviderAppointmentView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class ProviderAppointmentPresenter extends BasePresenter<ProviderAppointmentView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    ProviderAppointmentPresenter(RestData restData,PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void selectProvider(String service_id){
        getMvpView().showLoading();
        mRestData.getSelectProvider(service_id)
                .subscribe(new Subscriber<List<SelectProvider>>() {
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
                    public void onNext(List<SelectProvider> selectProviderList) {
                        try {
                            getMvpView().selectProvice(selectProviderList);
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
