package com.techart.smartsalon.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {
    private static SharedPreferences mPref;
    private static final String PREF_FILE_NAME = "Salon_pref_file";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_service_id = "PREF_service_id";
    private static final String PREF_service_name = "PREF_service_name";
    private static final String PREF_provider_id = "PREF_provider_id";
    private static final String PREF_category_id = "PREF_category_id";
    private static final String PREF_provider_name = "PREF_provider_name";
    private static final String PREF_date = "PREF_date";
    private static final String PREF_slot= "PREF_slot";
    private static final String PREF_package_type = "PREF_package_type";
    private static final String PREF_prepaid_id= "PREF_prepaid_id";
    private static final String PREF_FistName= "PREF_FistName";
    private static final String PREF_LastName= "PREF_LastName";
    private static final String PREF_Email= "PREF_Email";
    private static final String PREF_Phone= "PREF_Phone";
    private static final String PREF_Address= "PREF_Address";
    private static final String PREF_Avatar= "PREF_Avatar";
    private static final String PREF_PostCode= "PREF_PostCode";
    @Inject
    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }
    public void clear() {
        mPref.edit().clear().apply();
    }

    public void putToken(@NonNull String token) {
        mPref.edit().putString(PREF_KEY_ACCESS_TOKEN, token).apply();
    }
    @NonNull
    public String getToken() {
        return mPref.getString(PREF_KEY_ACCESS_TOKEN,"");
    }

    public void putUserId(@NonNull String userId) {
        mPref.edit().putString(PREF_KEY_USER_ID, userId).apply();
    }
    @NonNull
    public String getUserId() {
        return mPref.getString(PREF_KEY_USER_ID,"");
    }

    /*APPOINTMENT*/

    public void putService_id(@NonNull String service_id) {
        mPref.edit().putString(PREF_service_id, service_id).apply();
    }
    @NonNull
    public String getService_id() {
        return mPref.getString(PREF_service_id,"");
    }
    public void putServiceName(@NonNull String ServiceName) {
        mPref.edit().putString(PREF_service_name, ServiceName).apply();
    }
    @NonNull
    public String getServiceName() {
        return mPref.getString(PREF_service_name,"");
    }
    public void putProviderId(@NonNull String providerId) {
        mPref.edit().putString(PREF_provider_id, providerId).apply();
    }
    @NonNull
    public String getCategoryId() {
        return mPref.getString(PREF_category_id,"");
    }
    public void putCategoryID(@NonNull String categoryID) {
        mPref.edit().putString(PREF_category_id, categoryID).apply();
    }
    @NonNull
    public String getProviderId() {
        return mPref.getString(PREF_provider_id,"");
    }
    public void putProviderName(@NonNull String providerName) {
        mPref.edit().putString(PREF_provider_name, providerName).apply();
    }
    @NonNull
    public String getProviderName() {
        return mPref.getString(PREF_provider_name,"");
    }
    public void putDate(@NonNull String date) {
        mPref.edit().putString(PREF_date, date).apply();
    }
    @NonNull
    public String getDate() {
        return mPref.getString(PREF_date,"");
    }
    public void putSlot(@NonNull String slot) {
        mPref.edit().putString(PREF_slot, slot).apply();
    }
    @NonNull
    public String getSlot() {
        return mPref.getString(PREF_slot,"");
    }
    public void putPackage_type(@NonNull String package_type) {
        mPref.edit().putString(PREF_package_type, package_type).apply();
    }
    @NonNull
    public String getPackage_type() {
        return mPref.getString(PREF_package_type,"");
    }
    public void putPrepaid_id(@NonNull String prepaid_id) {
        mPref.edit().putString(PREF_prepaid_id, prepaid_id).apply();
    }
    @NonNull
    public String getPrepaid_id() {
        return mPref.getString(PREF_prepaid_id,"");
    }
    //User
    public void putFistName(@NonNull String userName) {
        mPref.edit().putString(PREF_FistName, userName).apply();
    }
    @NonNull
    public String getFistName() {
        return mPref.getString(PREF_FistName,"");
    }
    public void putLastName(@NonNull String userName) {
        mPref.edit().putString(PREF_LastName, userName).apply();
    }
    @NonNull
    public String getLastName() {
        return mPref.getString(PREF_LastName,"");
    }
    public void putPhone(@NonNull String userName) {
        mPref.edit().putString(PREF_Phone, userName).apply();
    }
    @NonNull
    public String getPhone() {
        return mPref.getString(PREF_Phone,"");
    }
    public void putEmail(@NonNull String email) {
        mPref.edit().putString(PREF_Email, email).apply();
    }
    @NonNull
    public String getEmail() {
        return mPref.getString(PREF_Email,"");
    }
    public void putAvatar(@NonNull String avatar) {
        mPref.edit().putString(PREF_Avatar, avatar).apply();
    }
    @NonNull
    public String getAvatar() {
        return mPref.getString(PREF_Avatar,"");
    }
    public void putAddress(@NonNull String address) {
        mPref.edit().putString(PREF_Address, address).apply();
    }
    @NonNull
    public String getAddress() {
        return mPref.getString(PREF_Address,"");
    }
    public void putPostcode(@NonNull String postcode) {
        mPref.edit().putString(PREF_PostCode, postcode).apply();
    }
    @NonNull
    public String getPostcode() {
        return mPref.getString(PREF_PostCode,"");
    }
}
