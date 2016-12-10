package com.techart.smartsalon.injector.module;

import com.techart.smartsalon.BaseApplication;
import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.rxjava.RxBus;
import com.techart.smartsalon.data.service.RestApi;
import com.techart.smartsalon.data.service.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

  protected final BaseApplication application;

  public ApplicationModule(BaseApplication application) {
    this.application = application;
  }
  @Provides
  @Singleton
  BaseApplication provideApplication() {
    return application;
  }
  @Provides
  @Singleton

  RestApi mRestApi() {
    return RestClient.sRestClient();
  }

  @Provides
  @Singleton
  RxBus mRxBus() {
    return new RxBus();
  }

  @Provides
  @Singleton
  PreferencesHelper mPreferencesHelper() {
    return new PreferencesHelper(application);
  }
}