package com.techart.smartsalon.mvp.presenter.loyalty;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.loyalty.PrepaidAppointment;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.loyalty.LoyaltyDetailsView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class LoyaltyDetailsPresenter extends BasePresenter<LoyaltyDetailsView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    LoyaltyDetailsPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void getLoyaltyDetails(String prepaid_id){
        getMvpView().showLoading();
        mRestData.getPrepaidPackageServices(prepaid_id)
                .subscribe(new Subscriber<List<PrepaidAppointment>>() {
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
                    public void onNext(List<PrepaidAppointment> appointmentList) {
                        try {
                            if(appointmentList.size()>0)
                                getMvpView().getLoyaltyDetails(appointmentList);
                            else
                                getMvpView().showError();
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
