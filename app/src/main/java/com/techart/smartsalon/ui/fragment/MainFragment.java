package com.techart.smartsalon.ui.fragment;

import android.support.v4.app.Fragment;

import com.techart.smartsalon.R;
import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.fragment.appointment.PrepaidPackagesFragment;
import com.techart.smartsalon.ui.fragment.appointment.SelectCategoryFragment;
import com.techart.smartsalon.ui.fragment.contact.ContactFragment;
import com.techart.smartsalon.ui.fragment.loyalty.LoyaltyFragment;
import com.techart.smartsalon.ui.fragment.myappointment.MyAppointmentFragment;
import com.techart.smartsalon.ui.fragment.offers.OffersFragment;
import com.techart.smartsalon.utils.Constant;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by Toan.IT
 * Date: 11/06/2016
 */

public class MainFragment extends BaseFragment {
    @Inject
    PreferencesHelper mPreferencesHelper;
    public static Fragment newInstance() {
        return new MainFragment();
    }
    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        ((MainActivity)getActivity()).getActivityComponent().inject(this);
    }
    @OnClick(R.id.tab_appointment)
    void tab_appointment(){
        mPreferencesHelper.putPackage_type(Constant.Package_type_Cash);
        mPreferencesHelper.putPrepaid_id(Constant.Prepaid_id);
        replaceFagment(getFragmentManager(),R.id.fragment, SelectCategoryFragment.newInstance());
    }
    @OnClick(R.id.tab_deal)
    void tab_deal(){
        mPreferencesHelper.putPackage_type(Constant.Package_type_Prepaid);
        mPreferencesHelper.putPrepaid_id(Constant.Prepaid_id);
        replaceFagment(getFragmentManager(),R.id.fragment, PrepaidPackagesFragment.newInstance());
    }
    @OnClick(R.id.tab_my_appointment)
    void tab_my_appointment(){
        replaceFagment(getFragmentManager(),R.id.fragment, MyAppointmentFragment.newInstance());
    }
    @OnClick(R.id.tab_offers)
    void tab_offers(){
        replaceFagment(getFragmentManager(),R.id.fragment, OffersFragment.newInstance());
    }
    @OnClick(R.id.tab_contact_us)
    void tab_contact_us(){
        replaceFagment(getFragmentManager(),R.id.fragment, ContactFragment.newInstance());
    }
    @OnClick(R.id.tab_earn_loyalty)
    void tab_earn_loyalty(){
        replaceFagment(getFragmentManager(),R.id.fragment, LoyaltyFragment.newInstance());
    }
}
