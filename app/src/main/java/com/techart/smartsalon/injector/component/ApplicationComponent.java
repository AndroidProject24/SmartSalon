package com.techart.smartsalon.injector.component;

import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.data.rxjava.RxBus;
import com.techart.smartsalon.data.service.RestData;
import com.techart.smartsalon.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {
    RestData mRestData();
    RxBus mRxBus();
    PreferencesHelper mPreferencesHelper();
}