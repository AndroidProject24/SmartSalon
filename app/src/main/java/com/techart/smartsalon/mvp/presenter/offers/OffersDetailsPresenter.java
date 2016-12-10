package com.techart.smartsalon.mvp.presenter.offers;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.offers.OffersDetails;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.offers.OffersDetailsView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class OffersDetailsPresenter extends BasePresenter<OffersDetailsView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    OffersDetailsPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void getOffersDetails(String offers_id){
        getMvpView().showLoading();
        mRestData.getOffersDetails(mPreferencesHelper.getUserId(),offers_id)
                .subscribe(new Subscriber<OffersDetails>() {
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
                    public void onNext(OffersDetails offersDetails) {
                        try {
                            if(offersDetails!=null)
                                getMvpView().getOffersDetails(offersDetails);
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
