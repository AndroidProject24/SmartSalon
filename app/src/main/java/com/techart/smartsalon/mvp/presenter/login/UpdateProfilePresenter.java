package com.techart.smartsalon.mvp.presenter.login;

import android.graphics.Bitmap;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.profile.Profile;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.login.UpdateProflieView;
import com.techart.smartsalon.utils.Utils;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class UpdateProfilePresenter extends BasePresenter<UpdateProflieView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    UpdateProfilePresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void getProfile(){
        getMvpView().showLoading();
        mRestData.getProfile(mPreferencesHelper.getUserId())
                .subscribe(new Subscriber<Profile>() {
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
                    public void onNext(Profile profile) {
                        try {
                            if(profile!=null)
                                getMvpView().getProfile(profile);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void updateProfile(String fist_name,String last_name,String phone,String address, String postcode,Bitmap avatar){
        getMvpView().showLoading();
        RequestBody requestBody=null;
        if(avatar!=null) {
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), Utils.ImageBase64(avatar));
        }
        mRestData.updateProfile(mPreferencesHelper.getUserId(),fist_name,last_name,phone,address,postcode,requestBody)
                .subscribe(new Subscriber<Profile>() {
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
                    public void onNext(Profile profile) {
                        try {
                            if(profile!=null) {
                                getMvpView().updateProfile(profile);
                            }else {
                                getMvpView().showError();
                            }
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
