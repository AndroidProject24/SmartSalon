package com.techart.smartsalon.mvp.presenter.appointment;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.appointment.ListCategory;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.appointment.SelectCategoryView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 10/12/2016
 */
public class SelectCategoryPresenter extends BasePresenter<SelectCategoryView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    SelectCategoryPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void selectCategory(){
        getMvpView().showLoading();
        mRestData.getCategory(mPreferencesHelper.getUserId())
                .subscribe(new Subscriber<List<ListCategory>>() {
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
                    public void onNext(List<ListCategory> listCategories) {
                        try {
                            if(listCategories.size()>0)
                                getMvpView().selectCategory(listCategories);
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
