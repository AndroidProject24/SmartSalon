package com.techart.smartsalon.mvp.presenter.appointment;

import android.os.StrictMode;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.mvp.model.appointment.CashOnDelivery;
import com.techart.smartsalon.mvp.presenter.base.BasePresenter;
import com.techart.smartsalon.mvp.view.appointment.CashAppointmentView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class CashAppointmentPresenter extends BasePresenter<CashAppointmentView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    CashAppointmentPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void cashDelivery(String category_id) {
        getMvpView().showLoading();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.smartsalon.net/mobile/v1/services?user_id="+mPreferencesHelper.getUserId()+"&category_id="+category_id)
                    .build();
            Response responses = null;
            responses = client.newCall(request).execute();
            if(responses.isSuccessful()) {
                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("payload");
                String array = Jarray.toString();
                String res = array.substring(1, array.length() - 1);
                JSONArray jsonArray = new JSONArray(res);
                List<CashOnDelivery> cashOnDeliveryList=new ArrayList<>();
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject c = jsonArray.getJSONObject(i);
                    CashOnDelivery cashOnDelivery=new CashOnDelivery();
                    cashOnDelivery.setId(c.getString("id"));
                    cashOnDelivery.setDuration(c.getString("duration"));
                    cashOnDelivery.setColor_tag(c.getString("color_tag"));
                    cashOnDelivery.setPrice(c.getString("price"));
                    cashOnDelivery.setDescription(c.getString("description"));
                    cashOnDelivery.setCurrency(c.getString("currency"));
                    cashOnDelivery.setName(c.getString("name"));
                    cashOnDeliveryList.add(cashOnDelivery);
                }
                getMvpView().cashOnDelivery(cashOnDeliveryList);
                getMvpView().hideLoading();
            }else{
                getMvpView().showError();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       /*mRestData.getCashDelivery(mPreferencesHelper.getUserId(), category_id)
                .subscribe(new Subscriber<List<CashOnDeliveryList>>() {
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
                    public void onNext(List<CashOnDeliveryList> cashOnDeliveryLists) {
                        try {
                            Logger.e("cashOnDeliveryLists=",cashOnDeliveryLists.toString());
                            Logger.e("cashOnDeliveryLists111=",cashOnDeliveryLists.get(0).getServices().toString());
                            //getMvpView().cashOnDelivery(cashOnDeliveryLists);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });*/
    }
    public PreferencesHelper getPreferencesHelper(){
        return mPreferencesHelper;
    }
}
