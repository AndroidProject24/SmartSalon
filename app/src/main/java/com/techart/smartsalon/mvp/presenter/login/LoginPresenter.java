package com.techart.smartsalon.mvp.presenter.login;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.login.Branch;
import com.techart.smartsalon.mvp.model.login.Login;
import com.techart.smartsalon.mvp.model.shop.Shops;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.login.LoginView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    LoginPresenter(RestData restData,PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void login(String email,String password,String shopId){
        getMvpView().showLoading();
        mRestData.getLogin(email,password,shopId)
                .subscribe(new Subscriber<Login>() {
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
                    public void onNext(Login login) {
                        try {
                            if(login.getId()!=null&&!login.getId().equalsIgnoreCase("")) {
                                getMvpView().login(login);
                                mPreferencesHelper.putUserId(login.getId());
                                mPreferencesHelper.putEmail(login.getEmail());
                            }else
                                getMvpView().login_error();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
  public void getBranch(){
    mRestData.getBranch()
        .subscribe(new Subscriber<List<Branch>>() {
          @Override
          public void onCompleted() {}

          @Override
          public void onError(Throwable e) {
            e.printStackTrace();
            getMvpView().showError();
          }

          @Override
          public void onNext(List<Branch> branchList) {
            if(branchList.size()>0) {
              getMvpView().getBranch(branchList);
            }
          }
        });
  }
    public void forgetPass(){
        getMvpView().showLoading();
        mRestData.getShops()
                .subscribe(new Subscriber<List<Shops>>() {
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
                    public void onNext(List<Shops> shopses) {
                        try {
                            getMvpView().login(null);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void send_token(String device_id,String token_id,String Os,String version,String model,String lat,String lng){
        mRestData.sendToken(mPreferencesHelper.getUserId(),device_id,token_id,Os,version,model,lat,lng);
    }
}
