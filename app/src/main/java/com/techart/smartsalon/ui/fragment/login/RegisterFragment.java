package com.techart.smartsalon.ui.fragment.login;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.register.Register;
import com.techart.smartsalon.mvp.presenter.login.RegisterPresenter;
import com.techart.smartsalon.mvp.view.login.RegisterView;
import com.techart.smartsalon.ui.activity.BaseActivity;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.utils.Constant;
import com.techart.smartsalon.utils.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.techart.smartsalon.utils.Utils.isEmailValid;
import static com.techart.smartsalon.utils.Utils.isPasswordValid;

/**
 * Created by Toan.IT
 * Date: 11/06/2016
 */

public class RegisterFragment extends BaseFragment implements RegisterView {
    @Inject
    RegisterPresenter
    mRegisterPresenter;
    @BindView(R.id.txtLogo)
    TextView mTxtLogo;
    @BindView(R.id.emailSignUp)
    EditText mEmailSignUp;
    @BindView(R.id.passwordSignUp)
    EditText mPasswordSignUp;
    @BindView(R.id.layout_email)
    TextInputLayout layout_email;
    @BindView(R.id.layout_pass)
    TextInputLayout layout_pass;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.layout_main)
    RelativeLayout layout_main;
    private Context mContext;
    public static Fragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.register_fragment;
    }

    @Override
    protected void initData() {
        mPasswordSignUp.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                mRegisterPresenter.checkEmail(mEmailSignUp.getText().toString());
            }
        });
    }

    @Override
    protected void initViews() {
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
        mRegisterPresenter.attachView(this);
    }

    @Override
    public void register(Register register) {
        mRegisterPresenter.getPreferencesHelper().putUserId(register.getId());
        mRegisterPresenter.send_token("","","","","","","","");
        replaceFagment(getFragmentManager(),R.id.fragment, UpdateProfileFragment.newInstance(true));
    }

    @Override
    public void checkEmail() {
        Snackbar.make(mEmailSignUp,"Email emty or exist!",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        if(mAvloadingIndicatorView!=null)
        mAvloadingIndicatorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if(mAvloadingIndicatorView!=null)
        mAvloadingIndicatorView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Utils.layoutError(mContext,layout_main,"Register failed!");
    }

    @Override
    public void showEmty() {

    }

    @OnClick(R.id.user_signup_button)
    void user_signup_button(){
        btn_Register();
    }
    private void btn_Register() {
        layout_email.setError(null);
        layout_pass.setError(null);
        String email = mEmailSignUp.getText().toString();
        String password = mPasswordSignUp.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            layout_pass.setErrorEnabled(true);
            layout_pass.setError(getString(R.string.error_invalid_password));
            focusView = layout_pass;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            layout_email.setErrorEnabled(true);
            layout_email.setError(getString(R.string.error_field_required));
            focusView = layout_email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            layout_email.setErrorEnabled(true);
            layout_email.setError(getString(R.string.error_invalid_email));
            focusView = layout_email;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            layout_email.setErrorEnabled(false);
            layout_pass.setErrorEnabled(false);
            mRegisterPresenter.register(mEmailSignUp.getText().toString(),mPasswordSignUp.getText().toString(), Constant.SHOP_ID);
        }
    }
}
