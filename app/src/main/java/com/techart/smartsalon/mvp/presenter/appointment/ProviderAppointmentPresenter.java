package com.techart.smartsalon.mvp.presenter.appointment;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.appointment.SelectProvider;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.appointment.ProviderAppointmentView;

import java.util.ArrayList;
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
                            if(selectProviderList.size()>0){
                                List<SelectProvider> selectProviders= new ArrayList<>();
                                SelectProvider selectProvider=new SelectProvider();
                                selectProvider.setId("-1");
                                selectProvider.setFirst_name("Any");
                                selectProvider.setLast_name("Provider");
                                selectProvider.setPhone("The provider will be chosen automatically");
                                selectProviders.add(selectProvider);
                                for(int i=0;i<selectProviderList.size();i++){
                                    SelectProvider selectProvider1=new SelectProvider();
                                    selectProvider1.setId(selectProviderList.get(i).getId());
                                    selectProvider1.setFirst_name(selectProviderList.get(i).getFirst_name());
                                    selectProvider1.setLast_name(selectProviderList.get(i).getLast_name());
                                    selectProvider1.setPhone(selectProviderList.get(i).getPhone());
                                    selectProvider1.setAvatar(selectProviderList.get(i).getAvatar());
                                    selectProvider1.setEmail(selectProviderList.get(i).getEmail());
                                    selectProviders.add(selectProvider1);
                                }
                                getMvpView().selectProvice(selectProviders);
                            }else
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
