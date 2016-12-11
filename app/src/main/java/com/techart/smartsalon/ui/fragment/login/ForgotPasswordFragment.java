package com.techart.smartsalon.ui.fragment.login;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.techart.smartsalon.R;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.presenter.login.ForgotPasswordPresenter;
import com.techart.smartsalon.mvp.view.login.ForgotPassView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.techart.smartsalon.utils.Utils.isEmailValid;

/**
 * Created by Toan.IT
 * Date: 17/06/2016
 */

public class ForgotPasswordFragment extends BaseFragment implements ForgotPassView {
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.txt_email)
    EditText mTxtEmail;
    @BindView(R.id.layout_txt_email)
    TextInputLayout layout_txt_email;
    @Inject
    ForgotPasswordPresenter
    mForgotPasswordPresenter;

    public static Fragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.forgotpass_fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mForgotPasswordPresenter.attachView(this);
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
        Snackbar.make(mTxtEmail, "Send email failed!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showEmty() {

    }

    @Override
    public void forgotPass() {
        Snackbar.make(mTxtEmail, "Reset password successfuly. Please check your email!", Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_forgot_email)
    void btn_forgot_email() {
        if (isEmailValid(mTxtEmail.getText().toString())&&mTxtEmail.getText().toString().length()>0) {
            layout_txt_email.setErrorEnabled(false);
            mForgotPasswordPresenter.forgotPass(mTxtEmail.getText().toString());
        }else {
            layout_txt_email.setErrorEnabled(true);
            layout_txt_email.setError(getString(R.string.error_invalid_email));
        }
    }
}