package com.techart.smartsalon.data.service;

import com.techart.smartsalon.mvp.model.appointment.BookAppointment;
import com.techart.smartsalon.mvp.model.appointment.CashOnDeliveryList;
import com.techart.smartsalon.mvp.model.appointment.ListCategory;
import com.techart.smartsalon.mvp.model.appointment.PrepaidPackages;
import com.techart.smartsalon.mvp.model.appointment.SelectProvider;
import com.techart.smartsalon.mvp.model.contact.Contact;
import com.techart.smartsalon.mvp.model.contact.Freedback;
import com.techart.smartsalon.mvp.model.login.Branch;
import com.techart.smartsalon.mvp.model.login.Login;
import com.techart.smartsalon.mvp.model.loyalty.Loyalty;
import com.techart.smartsalon.mvp.model.loyalty.PrepaidAppointment;
import com.techart.smartsalon.mvp.model.myappointment.AppointmentDetails;
import com.techart.smartsalon.mvp.model.myappointment.ListAppointment;
import com.techart.smartsalon.mvp.model.offers.Offers;
import com.techart.smartsalon.mvp.model.offers.OffersDetails;
import com.techart.smartsalon.mvp.model.profile.Profile;
import com.techart.smartsalon.mvp.model.register.Register;
import com.techart.smartsalon.mvp.model.shop.Shops;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toan.it
 * Date: 25/05/2016
 */
@Singleton
public class RestData {
    private final RestApi mRestApi;
    @Inject
    public RestData(RestApi restApi) {
        mRestApi = restApi;
    }

    //Branch
    public Observable<List<Branch>> getBranch() {
        return mRestApi.getBranch("5")
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //Login
    public Observable<Login> getLogin(String email,String password,String shopId) {
        return mRestApi.getLogin(email,password,shopId)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<ResponseBody> forgotPassword(String email,String shopId) {
        return mRestApi.forgotPassword(email,shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //Register
    public Observable<ResponseBody> checkEmail(String email, String shopId) {
        return mRestApi.checkEmail(email,shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Register> getRegister(String email,String password,String shopId) {
        return mRestApi.getRegister(email,password,shopId)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //Update profile
    public Observable<Profile> getProfile(String use_id) {
        return mRestApi.getAccount(use_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<Profile> updateProfile(String use_id, String fist_name, String last_name, String phone, String address, String postcode, RequestBody avatar) {
        return mRestApi.updateAccount(use_id,fist_name,last_name,phone,address,postcode,avatar)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //Shops
    public Observable<List<Shops>> getShops() {
        return mRestApi.getShops()
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<Shops>> getShopDetail(String id) {
        return mRestApi.getShopDetails(id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //Appointment
    public Observable<List<ListCategory>> getCategory(String user_id) {
        return mRestApi.getCategory(user_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<CashOnDeliveryList>> getCashDelivery(String user_id, String category_id) {
        return mRestApi.getService(user_id,category_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<SelectProvider>> getSelectProvider(String service_id) {
        return mRestApi.getProviders(service_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<String>> getSlot(String service_id,String provider_id,String date) {
        return mRestApi.getSlots(service_id,provider_id,date)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<String>> getAllSlot(String service_id,String shop_id,String date) {
        return mRestApi.getAllProviderSlots(service_id,shop_id,date)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<BookAppointment> getBook(String user_id,String service_id,String prepaid_id, String provider_id, String date,String slot,String type,String comment) {
        return mRestApi.getBook(user_id,service_id,prepaid_id,provider_id,date,slot,type,comment)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<PrepaidPackages>> getPrepaidPackages(String user_id) {
        return mRestApi.getPrepaidPackages(user_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //MyAppointment
    public Observable<List<ListAppointment>> getMyAppointments(String user_id,String date) {
        return mRestApi.getAppointments(user_id,date)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<ListAppointment>> getAppointmentsHistory(String user_id) {
        return mRestApi.getAppointmentsHistory(user_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<AppointmentDetails> getMyAppointmentDetails(String appointment_id) {
        return mRestApi.getAppointmentById(appointment_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //Offers
    public Observable<List<Offers>> getOffers(String user_id) {
        return mRestApi.getOffers(user_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<OffersDetails> getOffersDetails(String user_id, String offers_id) {
        return mRestApi.getOffersRead(user_id,offers_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //Loyalty
    public Observable<Loyalty> getLoyalty(String user_id) {
        return mRestApi.getLoyalty(user_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<PrepaidAppointment>> getPrepaidPackageServices(String prepaid_id) {
        return mRestApi.getPrepaidPackageServices(prepaid_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //Contact
    public Observable<Freedback> sendFreedback(String user_id,String subject,String content) {
        return mRestApi.sendFeedback(user_id,subject,content)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<List<Contact>> getContactList(String user_id) {
        return mRestApi.getContactList(user_id)
                .map(data->data.payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //Send token
    public Observable<ResponseBody> sendToken(String user_id,String device_id,String token_id,String Os,String version,String model,String lat,String lng) {
        return mRestApi.registerGCM(user_id,device_id,token_id,Os,version,model,lat,lng)
                .subscribeOn(Schedulers.newThread());
    }
}
