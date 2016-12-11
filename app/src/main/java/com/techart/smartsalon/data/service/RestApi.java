package com.techart.smartsalon.data.service;

import com.techart.smartsalon.mvp.model.JsonArray;
import com.techart.smartsalon.mvp.model.JsonObject;
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

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */

public interface RestApi {
    String BASE_URL = "https://api.smartsalon.net/mobile/v1/";

    /*BRANCHES*/
    @GET("branches")
    Observable<JsonArray<Branch>> getBranch(@Query("shop_id") String shop_id);

    /*LOGIN*/
    @POST("login")
    Observable<JsonObject<Login>> getLogin(@Query("email") String email, @Query("password") String password, @Query("shop_id") String shop_id);

    @POST("request-password")
    Observable<ResponseBody> forgotPassword(@Query("email") String email, @Query("shop_id")String shop_id);

    /*REGISTER*/
    @POST("validate")
    Observable<ResponseBody> checkEmail(@Query("email") String email, @Query("shop_id") String shop_id);

    @GET("register")
    Observable<JsonObject<Register>> getRegister(@Query("email") String email, @Query("password") String password, @Query("shop_id") String shop_id);

    /*MYAPPOINTMENT*/ //user_id=53&date=2016-06-12
    @GET("appointments")
    Observable<JsonArray<ListAppointment>> getAppointments(@Query("user_id") String user_id, @Query("date") String date);

    @GET("appointment")
    Observable<JsonObject<AppointmentDetails>> getAppointmentById(@Query("appointment_id") String appointment_id);

    @GET("appointments")
    Observable<JsonArray<ListAppointment>> getAppointmentsHistory(@Query("user_id") String user_id);

    /*APPOINTMENT*/

    @GET("categories")
    Observable<JsonArray<ListCategory>> getCategory(@Query("user_id") String user_id);

    @GET("services")
    Observable<JsonArray<CashOnDeliveryList>> getService(@Query("user_id") String user_id, @Query("category_id") String category_id);

    @GET("providers")
    Observable<JsonArray<SelectProvider>> getProviders(@Query("service_id") String service_id);

    @GET("slots")
    Observable<JsonArray<String>> getSlots(@Query("service_id") String service_id,@Query("provider_id") String provider_id,@Query("date") String date);

    @GET("all-slots")
    Observable<JsonArray<String>> getAllProviderSlots(@Query("service_id") String service_id, @Query("shop_id") String shop_id,@Query("date") String date);

    @POST("book")
    Observable<JsonObject<BookAppointment>> getBook(@Query("user_id") String user_id, @Query("service_id") String service_id,@Query("prepaid_id") String prepaid_id, @Query("provider_id") String provider_id, @Query("date") String date,
                                                 @Query("slot") String slot, @Query("type") String type, @Query("comment") String comment);

    /*LOYALTY*/
    @GET("loyalty")
    Observable<JsonObject<Loyalty>> getLoyalty(@Query("user_id") String user_id);
    /*SHOP*/
    @GET("shops")
    Observable<JsonArray<Shops>> getShops();

    @GET("shop")
    Observable<JsonArray<Shops>> getShopDetails(@Query("shop_id") String shop_id);

    /*ACCOUNT INFO*/
    @POST("account")
    Observable<JsonObject<Profile>> getAccount(@Query("user_id") String user_id);

    @Multipart
    @POST("account")
    Observable<JsonObject<Profile>> updateAccount(@Part(value = "user_id") String user_id, @Part(value = "first_name") String first_name,@Part(value = "last_name") String last_name,@Part(value = "phone") String phone,
                                                  @Part(value = "address") String address,@Part(value = "postcode") String postcode,@Part("photo\"; filename=\"image.png\" ") RequestBody photo);

    /*CONTACT*/
    @POST("send-contact")
    Observable<JsonObject<Freedback>> sendFeedback(@Query("user_id") String user_id, @Query("subject") String subject, @Query("content") String content);

    @GET("contact-list")
    Observable<JsonArray<Contact>> getContactList(@Query("user_id") String user_id);

    /* PUSH & TRACKING*/
    @POST("register-token")
    Observable<ResponseBody> registerGCM(@Query("user_id") String userId,@Query("device_id") String deviceId,@Query("token_id")String tokenId,@Query("os") String os,
                                   @Query("version") String version,@Query("model") String model,@Query("lat") String lat,@Query("lng") String lng );
    /*OFFER*/
    @GET("offers")
    Observable<JsonArray<Offers>> getOffers(@Query("user_id") String user_id);

    @GET("offer-read")
    Observable<JsonObject<OffersDetails>> getOffersRead(@Query("user_id") String user_id, @Query("offer_id") String offer_id);

    /*PREPAID PACKAGE*/
    @GET("prepaid-package")
    Observable<JsonArray<PrepaidPackages>> getPrepaidPackages(@Query("user_id") String user_id);

    @GET("prepaid-appointment")
    Observable<JsonArray<PrepaidAppointment>> getPrepaidPackageServices(@Query("prepaid_id") String prepaid_id);
}
