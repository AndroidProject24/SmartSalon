package com.techart.smartsalon.mvp.presenter.appointment;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.appointment.PrepaidPackages;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.appointment.PrepaidPackageView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class PrepaidPackagePresenter extends BasePresenter<PrepaidPackageView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    PrepaidPackagePresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void getPrepaidPackages(){
        getMvpView().showLoading();
        mRestData.getPrepaidPackages(mPreferencesHelper.getUserId())
                .subscribe(new Subscriber<List<PrepaidPackages>>() {
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
                    public void onNext(List<PrepaidPackages> prepaidPackagesList) {
                        try {
                            getMvpView().getPrepaidPackages(prepaidPackagesList);
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
