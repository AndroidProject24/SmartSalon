package com.techart.smartsalon.mvp.presenter.offers;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.offers.Offers;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.offers.OffersView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class OffersPresenter extends BasePresenter<OffersView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    OffersPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void getOffers(){
        getMvpView().showLoading();
        mRestData.getOffers(mPreferencesHelper.getUserId())
                .subscribe(new Subscriber<List<Offers>>() {
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
                    public void onNext(List<Offers> offersList) {
                        try {
                            if(offersList.size()>0)
                                getMvpView().getOffers(offersList);
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
