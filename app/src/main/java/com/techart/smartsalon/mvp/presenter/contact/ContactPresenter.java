package com.techart.smartsalon.mvp.presenter.contact;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.contact.Contact;
import com.techart.smartsalon.mvp.model.contact.Freedback;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.contact.ContactView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class ContactPresenter extends BasePresenter<ContactView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    ContactPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void getContactList(){
        getMvpView().showLoading();
        mRestData.getContactList(mPreferencesHelper.getUserId())
                .subscribe(new Subscriber<List<Contact>>() {
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
                    public void onNext(List<Contact> contactList) {
                        try {
                            if(contactList.size()>0)
                                getMvpView().getContactList(contactList);
                            else
                                getMvpView().showError();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void sendFreedback(String subject,String content){
        getMvpView().showLoading();
        mRestData.sendFreedback(mPreferencesHelper.getUserId(),subject,content)
                .subscribe(new Subscriber<Freedback>() {
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
                    public void onNext(Freedback freedback) {
                        try {
                            if(freedback!=null)
                                 getMvpView().sendFreedback(freedback);
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
