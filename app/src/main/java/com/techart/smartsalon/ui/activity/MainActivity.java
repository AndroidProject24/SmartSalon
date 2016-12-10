package com.techart.smartsalon.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.techart.smartsalon.R;
import com.techart.smartsalon.data.local.PreferencesHelper;
import com.techart.smartsalon.interfaces.KeyListener;
import com.techart.smartsalon.interfaces.OnBackListener;
import com.techart.smartsalon.interfaces.ToolbarTitleListener;
import com.techart.smartsalon.ui.fragment.MainFragment;
import com.techart.smartsalon.ui.fragment.contact.ContactFragment;
import com.techart.smartsalon.ui.fragment.login.ForgotPasswordFragment;
import com.techart.smartsalon.ui.fragment.login.LoginFragment;
import com.techart.smartsalon.ui.fragment.login.UpdateProfileFragment;
import com.techart.smartsalon.ui.fragment.myappointment.AppointmentHistoryFragment;

import javax.inject.Inject;

import butterknife.BindView;

import static com.techart.smartsalon.R.id.toolbar;

public class MainActivity extends BaseActivity implements ToolbarTitleListener{
    @BindView(toolbar)
    Toolbar mToolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private boolean doubleBackToExitPressedOnce;
    @Inject
    PreferencesHelper mPreferencesHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        initDrawer(savedInstanceState);
    }
    private void initDrawer(Bundle savedInstanceState){
        final IProfile profile = new ProfileDrawerItem().withName(mPreferencesHelper.getFistName()+" "+mPreferencesHelper.getLastName()).withEmail(mPreferencesHelper.getEmail()).withIcon(mPreferencesHelper.getAvatar()).withIdentifier(100);
        //final IProfile profile = new ProfileDrawerItem().withName("Huỳnh văn toàn").withEmail("huynhvantoan.itc@gmail.com").withIcon("https://fbcdn-photos-c-a.akamaihd.net/hphotos-ak-xfa1/v/t1.0-0/p206x206/11751426_794373917343882_7256246783339720174_n.jpg?oh=ae7a8b6ffbc38bd09c7215313ca79d09&oe=5808AD45&__gda__=1475369879_a3724b4a61997de0523aba09cfd19f82").withIdentifier(100);
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                //.withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.material_bg_account)
                .addProfiles(profile)
                .withSavedInstance(savedInstanceState)
                .build();
        result = new DrawerBuilder(this)
                .withActivity(this)
                .withToolbar(mToolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult)
                .withRootView(R.id.drawer_container)
                .withDisplayBelowStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIcon(R.mipmap.ic_home).withIdentifier(1).withSetSelected(true),
                        new PrimaryDrawerItem().withName("Appointment History").withIcon(R.mipmap.ic_history).withIdentifier(2).withSetSelected(true),
                        new SectionDrawerItem().withName("Account"),
                        new PrimaryDrawerItem().withName("Profile").withIcon(R.mipmap.ic_profile).withIdentifier(3).withSetSelected(true),
                        new PrimaryDrawerItem().withName("Change password").withIcon(R.mipmap.ic_change_pass).withIdentifier(4).withSetSelected(true).withEnabled(true),
                        new SectionDrawerItem().withName("Help"),
                        new PrimaryDrawerItem().withName("Contact").withIcon(R.mipmap.ic_contact).withIdentifier(5).withSetSelected(true)
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    if (drawerItem != null) {
                        if (!mPreferencesHelper.getUserId().equalsIgnoreCase("")) {
                            if (drawerItem.getIdentifier() == 1) {
                                replaceFagment(getSupportFragmentManager(), R.id.fragment, MainFragment.newInstance());
                            } else if (drawerItem.getIdentifier() == 2) {
                                replaceFagment(getSupportFragmentManager(), R.id.fragment, AppointmentHistoryFragment.newInstance());
                            } else if (drawerItem.getIdentifier() == 3) {
                                replaceFagment(getSupportFragmentManager(), R.id.fragment, UpdateProfileFragment.newInstance(false));
                            } else if (drawerItem.getIdentifier() == 4) {
                                replaceFagment(getSupportFragmentManager(), R.id.fragment, ForgotPasswordFragment.newInstance());
                            } else if (drawerItem.getIdentifier() == 5) {
                                replaceFagment(getSupportFragmentManager(), R.id.fragment, ContactFragment.newInstance());
                            }
                        }else{
                            replaceFagment(getSupportFragmentManager(), R.id.fragment, LoginFragment.newInstance());
                            Snackbar.make(mToolbar, "Please login!", Snackbar.LENGTH_LONG).show();
                        }
                    }
                    return false;
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }
    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void initViews() {
        if(mPreferencesHelper.getUserId().equalsIgnoreCase("")) {
            addFagment(getSupportFragmentManager(), R.id.fragment, LoginFragment.newInstance());
        }else{
            addFagment(getSupportFragmentManager(), R.id.fragment, MainFragment.newInstance());
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void injectDependencies() {
            getActivityComponent().inject(this);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            String currentTag = getSupportFragmentManager().findFragmentById(R.id.fragment).getTag();
            if (currentTag.equals(MainFragment.class.getName()) || currentTag.equals(LoginFragment.class.getName())) {
                if (doubleBackToExitPressedOnce) {
                    finish();
                }
                this.doubleBackToExitPressedOnce = true;
                Snackbar.make(mToolbar,R.string.msg_exit,Snackbar.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            } else {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
                if (fragment instanceof OnBackListener) {
                    ((OnBackListener) fragment).onBackPress();
                }
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK ) {
            onBackPressed();
        }
        else if(getSupportFragmentManager().findFragmentById(R.id.fragment) instanceof KeyListener) {
            KeyListener keyListener = (KeyListener) getSupportFragmentManager().findFragmentById(R.id.fragment);
            keyListener.onKeyDown(keyCode, event);
        }
        return false;
    }

    @Override
    public void changeTitle(String name) {
        mToolbar.setTitle(name);
    }
}
