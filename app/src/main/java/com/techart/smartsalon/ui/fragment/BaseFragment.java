package com.techart.smartsalon.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.techart.smartsalon.R;
import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.interfaces.OnBackListener;
import com.techart.smartsalon.interfaces.ToolbarTitleListener;
import com.techart.smartsalon.mvp.model.register.Register;
import com.techart.smartsalon.ui.fragment.appointment.BookAppointmentFragment;
import com.techart.smartsalon.ui.fragment.appointment.CashAppointmentFragment;
import com.techart.smartsalon.ui.fragment.appointment.PrepaidPackagesFragment;
import com.techart.smartsalon.ui.fragment.appointment.SelectCategoryFragment;
import com.techart.smartsalon.ui.fragment.appointment.SelectProviderFragment;
import com.techart.smartsalon.ui.fragment.appointment.SelectSlotFragment;
import com.techart.smartsalon.ui.fragment.contact.ContactFragment;
import com.techart.smartsalon.ui.fragment.login.LoginFragment;
import com.techart.smartsalon.ui.fragment.login.RegisterFragment;
import com.techart.smartsalon.ui.fragment.loyalty.LoyaltyDetailsFragment;
import com.techart.smartsalon.ui.fragment.loyalty.LoyaltyFragment;
import com.techart.smartsalon.ui.fragment.myappointment.AppointmentDetailsFragment;
import com.techart.smartsalon.ui.fragment.myappointment.AppointmentHistoryFragment;
import com.techart.smartsalon.ui.fragment.myappointment.MyAppointmentFragment;
import com.techart.smartsalon.ui.fragment.offers.OffersDetailsFragment;
import com.techart.smartsalon.ui.fragment.offers.OffersFragment;
import com.techart.smartsalon.utils.Constant;
import com.techart.smartsalon.utils.Logger;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by Toan.IT
 * Date:22/5/2016
 */
public abstract class BaseFragment extends Fragment implements OnBackListener{
    private View mContentView;
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private Subscription subscription;
    private CompositeSubscription mCompositeSubscription;
    private Unbinder unbinder;
    private String TAG = getTAG();
    protected abstract String getTAG();
    private ToolbarTitleListener toolbarTitleListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            toolbarTitleListener = (ToolbarTitleListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG);
        if (null == mContentView) {
            mContentView = inflater.inflate(setLayoutResourceID(),container, false);
        }
        unbinder = ButterKnife.bind(this, mContentView);
        mContext = getContext();
        mProgressDialog = new ProgressDialog(getMContext());
        mProgressDialog.setCanceledOnTouchOutside(false);
        initViews();
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        setTitle();
    }
    private void setTitle(){
        String currenttag=getFragmentManager().findFragmentById(R.id.fragment).getTag();
        if(currenttag.equalsIgnoreCase(MyAppointmentFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Upcomming appointments");
        }else if(currenttag.equalsIgnoreCase(AppointmentDetailsFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Details");
        }else if(currenttag.equalsIgnoreCase(OffersFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Offers");
        }else if(currenttag.equalsIgnoreCase(OffersDetailsFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Offers Details");
        }else if(currenttag.equalsIgnoreCase(LoyaltyFragment.class.getName())||currenttag.equalsIgnoreCase(LoyaltyDetailsFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Loyalty");
        }else if(currenttag.equalsIgnoreCase(ContactFragment.class.getName())) {
            toolbarTitleListener.changeTitle(getResources().getString(R.string.contact_us));
        }else if(currenttag.equalsIgnoreCase(SelectCategoryFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Choose Category");
        }else if(currenttag.equalsIgnoreCase(CashAppointmentFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Choose service (2 of 5)");
        }else if(currenttag.equalsIgnoreCase(PrepaidPackagesFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Choose prepaid package (2 of 5)");
        }else if(currenttag.equalsIgnoreCase(SelectProviderFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Choose providers (3 of 5)");
        }else if(currenttag.equalsIgnoreCase(SelectSlotFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Choose Slot (4 of 5)");
        }else if(currenttag.equalsIgnoreCase(BookAppointmentFragment.class.getName())) {
            toolbarTitleListener.changeTitle("Confirm Booking (5 of 5)");
        }else if(currenttag.equalsIgnoreCase(Register.class.getName())) {
            toolbarTitleListener.changeTitle("Register");
        }else if(currenttag.equalsIgnoreCase(AppointmentHistoryFragment.class.getName())) {
            toolbarTitleListener.changeTitle("History");
        }else{
            toolbarTitleListener.changeTitle(getResources().getString(R.string.app_name));
        }
    }
    protected abstract void initViews();

    protected abstract int setLayoutResourceID();

    protected abstract void initData();

    protected View getContentView() {
        return mContentView;
    }

    private Context getMContext() {
        return mContext;
    }

    protected ProgressDialog getProgressDialog() {
        return mProgressDialog;
    }


    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }

    public void addSubscription(Subscription s) {
        if (s == null) {
            return;
        }
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }
    public void replaceFagment(@NonNull FragmentManager fragmentManager, int frameId, @NonNull Fragment fragment){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment,fragment.getClass().getName());
        transaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d(TAG);
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG);
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d(TAG);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d(TAG);
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.d(TAG);
        unsubscribe();
        Glide.get(mContext).clearMemory();
    }

    protected void checkException(Throwable e) {
        //NetUtils.checkHttpException(getContext(), e, mRootView);
    }

    private void unsubscribe() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
      /*  RefWatcher refWatcher = BaseApplication.getRefWatcher();
        refWatcher.watch(this);*/
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public void onBackPress() {
        String currenttag=getFragmentManager().findFragmentById(R.id.fragment).getTag();
        Logger.e(currenttag);
        if(currenttag.equalsIgnoreCase(OffersDetailsFragment.class.getName())) {
            replaceFagment(getFragmentManager(),R.id.fragment, OffersFragment.newInstance());
        }else if(currenttag.equalsIgnoreCase(AppointmentDetailsFragment.class.getName())) {
            replaceFagment(getFragmentManager(),R.id.fragment, MyAppointmentFragment.newInstance());
        }else if(currenttag.equalsIgnoreCase(LoyaltyDetailsFragment.class.getName())) {
            replaceFagment(getFragmentManager(),R.id.fragment, LoyaltyFragment.newInstance());
        }else if(currenttag.equalsIgnoreCase(CashAppointmentFragment.class.getName())) {
            replaceFagment(getFragmentManager(),R.id.fragment, SelectCategoryFragment.newInstance());
        } else if(currenttag.equalsIgnoreCase(BookAppointmentFragment.class.getName())) {
            replaceFagment(getFragmentManager(),R.id.fragment, SelectSlotFragment.newInstance());
        }else if(currenttag.equalsIgnoreCase(SelectSlotFragment.class.getName())) {
            replaceFagment(getFragmentManager(),R.id.fragment, SelectProviderFragment.newInstance());
        }else if(currenttag.equalsIgnoreCase(RegisterFragment.class.getName())) {
            replaceFagment(getFragmentManager(),R.id.fragment, LoginFragment.newInstance());
        }else if(currenttag.equalsIgnoreCase(SelectProviderFragment.class.getName())) {
            PreferencesHelper preferencesHelper=new PreferencesHelper(mContext);
            if(preferencesHelper.getPrepaid_id().equalsIgnoreCase(Constant.Package_type_Prepaid)) {
                replaceFagment(getFragmentManager(), R.id.fragment, PrepaidPackagesFragment.newInstance());
            }else{
                replaceFagment(getFragmentManager(), R.id.fragment, CashAppointmentFragment.newInstance(preferencesHelper.getCategoryId()));
            }
        }else{
            _removeWorkerFragments();
            replaceFagment(getFragmentManager(),R.id.fragment, MainFragment.newInstance());
        }
    }
    private void _removeWorkerFragments() {
        Fragment fragment=null;
        fragment = getFragmentManager().findFragmentByTag(RegisterFragment.class.getName());
        if(fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        fragment = getFragmentManager().findFragmentByTag(OffersFragment.class.getName());
        if(fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        fragment = getFragmentManager().findFragmentByTag(OffersDetailsFragment.class.getName());
        if(fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        fragment = getFragmentManager().findFragmentByTag(MyAppointmentFragment.class.getName());
        if(fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        fragment = getFragmentManager().findFragmentByTag(AppointmentDetailsFragment.class.getName());
        if(fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        fragment = getFragmentManager().findFragmentByTag(LoyaltyFragment.class.getName());
        if(fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        fragment = getFragmentManager().findFragmentByTag(LoyaltyDetailsFragment.class.getName());
        if(fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        fragment = getFragmentManager().findFragmentByTag(ContactFragment.class.getName());
        if(fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
}
