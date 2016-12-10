package com.techart.smartsalon.injector.component;

import com.techart.smartsalon.injector.module.ActivityModule;
import com.techart.smartsalon.injector.qualifier.PerActivity;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.fragment.MainFragment;
import com.techart.smartsalon.ui.fragment.appointment.BookAppointmentFragment;
import com.techart.smartsalon.ui.fragment.appointment.CashAppointmentFragment;
import com.techart.smartsalon.ui.fragment.appointment.PrepaidPackagesFragment;
import com.techart.smartsalon.ui.fragment.appointment.SelectCategoryFragment;
import com.techart.smartsalon.ui.fragment.appointment.SelectProviderFragment;
import com.techart.smartsalon.ui.fragment.appointment.SelectSlotFragment;
import com.techart.smartsalon.ui.fragment.contact.ContactFragment;
import com.techart.smartsalon.ui.fragment.login.ForgotPasswordFragment;
import com.techart.smartsalon.ui.fragment.login.LoginFragment;
import com.techart.smartsalon.ui.fragment.login.RegisterFragment;
import com.techart.smartsalon.ui.fragment.login.UpdateProfileFragment;
import com.techart.smartsalon.ui.fragment.loyalty.LoyaltyDetailsFragment;
import com.techart.smartsalon.ui.fragment.loyalty.LoyaltyFragment;
import com.techart.smartsalon.ui.fragment.myappointment.AppointmentDetailsFragment;
import com.techart.smartsalon.ui.fragment.myappointment.AppointmentHistoryDetailsFragment;
import com.techart.smartsalon.ui.fragment.myappointment.AppointmentHistoryFragment;
import com.techart.smartsalon.ui.fragment.myappointment.MyAppointmentFragment;
import com.techart.smartsalon.ui.fragment.offers.OffersDetailsFragment;
import com.techart.smartsalon.ui.fragment.offers.OffersFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(MainActivity mainActivity);

  void inject(MainFragment mainFragment);
  /*LOGIN*/
  void inject(LoginFragment loginFragment);

  void inject(RegisterFragment registerFragment);

  void inject(ForgotPasswordFragment forgotPasswordFragment);

  /*PROFILE*/

  void inject(UpdateProfileFragment updateProfileFragment);

  /*APPOINTMENT*/

  void inject(BookAppointmentFragment bookAppointmentFragment);

  void inject(SelectCategoryFragment selectCategoryFragment);

  void inject(PrepaidPackagesFragment prepaidPackagesFragment);

  void inject(CashAppointmentFragment cashOnDeliveryFragment);

  void inject(SelectProviderFragment selectProviderFragment);

  void inject(SelectSlotFragment selectSlotFragment);

    /*MYAPPOINTMENT*/
  void inject(MyAppointmentFragment myAppointmentFragment);

  void inject(AppointmentDetailsFragment appointmentDetailsFragment);

  void inject(AppointmentHistoryDetailsFragment appointmentHistoryDetailsFragment);

  void inject(AppointmentHistoryFragment appointmentHistoryFragment);

  /*OFFERS*/
  void inject(OffersFragment offersFragment);

  void inject(OffersDetailsFragment offersFragment);

  /*LOYALTY*/
  void inject(LoyaltyFragment loyaltyFragment);

  void inject(LoyaltyDetailsFragment loyaltyDetailsFragment);

  /*CONTACT*/
  void inject(ContactFragment contactFragment);
}