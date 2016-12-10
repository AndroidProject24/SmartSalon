package com.techart.smartsalon.mvp.presenter.login;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.register.Register;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.login.RegisterView;
import com.techart.smartsalon.utils.Constant;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    RegisterPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void checkEmail(String email){
        getMvpView().showLoading();
        mRestData.checkEmail(email, Constant.SHOP_ID)
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getMvpView().hideLoading();
                        getMvpView().checkEmail();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            if(responseBody==null)
                            getMvpView().checkEmail();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void register(String email,String password,String shopId){
        getMvpView().showLoading();
        mRestData.getRegister(email,password,shopId)
                .subscribe(new Subscriber<Register>() {
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
                    public void onNext(Register register) {
                        try {
                            getMvpView().register(register);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void send_token(String user_id,String device_id,String token_id,String Os,String version,String model,String lat,String lng){
        mRestData.sendToken(user_id,device_id,token_id,Os,version,model,lat,lng);
    }
    public PreferencesHelper getPreferencesHelper(){
        return mPreferencesHelper;
    }
}
