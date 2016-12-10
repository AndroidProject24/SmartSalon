package com.techart.smartsalon.mvp.presenter.login;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.login.ForgotPassView;
import com.techart.smartsalon.utils.Constant;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class ForgotPasswordPresenter extends BasePresenter<ForgotPassView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    ForgotPasswordPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void forgotPass(String email){
        getMvpView().showLoading();
        mRestData.forgotPassword(email, Constant.SHOP_ID)
                .subscribe(new Subscriber<ResponseBody>() {
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
                    public void onNext(ResponseBody responseBody) {
                        try {
                            if(responseBody!=null)
                                getMvpView().forgotPass();
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
