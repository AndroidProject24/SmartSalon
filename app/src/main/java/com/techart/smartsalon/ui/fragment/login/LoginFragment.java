package com.techart.smartsalon.ui.fragment.login;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemLoginListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.login.Branch;
import com.techart.smartsalon.mvp.model.login.Login;
import com.techart.smartsalon.mvp.presenter.login.LoginPresenter;
import com.techart.smartsalon.mvp.view.login.LoginView;
import com.techart.smartsalon.ui.activity.BaseActivity;
import com.techart.smartsalon.ui.adapter.BranchAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.ui.fragment.MainFragment;
import com.techart.smartsalon.utils.Constant;
import com.techart.smartsalon.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.techart.smartsalon.utils.Utils.isEmailValid;
import static com.techart.smartsalon.utils.Utils.isPasswordValid;

public class LoginFragment extends BaseFragment implements LoginView,ClickItemLoginListener {
    @Inject
    LoginPresenter
    mLoginPresenter;
    @BindView(R.id.txtLogo)
    TextView mTxtLogo;
    @BindView(R.id.txt_email)
    EditText mTxtEmail;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.branch)
    EditText mBranch;
    @BindView(R.id.layout_email)
    TextInputLayout layout_email;
    @BindView(R.id.layout_pass)
    TextInputLayout layout_pass;
    @BindView(R.id.custom_signin_button)
    AppCompatButton mCustomSigninButton;
    @BindView(R.id.login_fb_button)
    AppCompatButton mLoginFbButton;
    @BindView(R.id.login_google_button)
    AppCompatButton mLoginGoogleButton;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    @BindView(R.id.login_layout)
    RelativeLayout mLoginLayout;
    private Context mContext;
    private List<Branch> branchList;
    public static Fragment newInstance() {
        return new LoginFragment();
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
    protected void initViews() {
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
        mLoginPresenter.attachView(this);
        mLoginPresenter.getBranch();
        mTxtEmail.addTextChangedListener(textWatcher);
        mPassword.addTextChangedListener(textWatcher);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.login_fragment;
    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mLoginGoogleButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.google_plus_vector, 0, 0, 0);
            mLoginFbButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.facebook_vector, 0, 0, 0);
        } else {
            mLoginGoogleButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_google_plus_white_36dp, 0, 0, 0);
            mLoginFbButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_facebook_white_36dp, 0, 0, 0);
        }

    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            enableLoginBtn();
        }
    };

    private void enableLoginBtn() {
        mCustomSigninButton.setEnabled(mTxtEmail.length() != 0 && mPassword.getText().length() != 0);
    }

    @OnClick(R.id.custom_signin_button)
    void sign_in() {
        attemptLogin();
    }

    private void attemptLogin() {
        mTxtEmail.setError(null);
        mPassword.setError(null);
        String email = mTxtEmail.getText().toString();
        String password = mPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            layout_pass.setErrorEnabled(true);
            layout_pass.setError(getString(R.string.error_invalid_password));
            focusView = layout_pass;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
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
            mLoginPresenter.login(mTxtEmail.getText().toString(),mPassword.getText().toString(), Constant.SHOP_ID);
        }
    }
    @Override
    public void showLoading() {
        mAvloadingIndicatorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mAvloadingIndicatorView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Snackbar.make(mLoginLayout,"Login Fail!",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showEmty() {

    }

    @Override public void getBranch(List<Branch> branchList) {
    if(branchList.size()>0) {
      Constant.SHOP_ID=branchList.get(0).getId();
      mBranch.setText(branchList.get(0).getName());
      this.branchList = branchList;
    }
  }

  @Override
    public void login(Login login) {
        mLoginPresenter.send_token(Utils.getDeviceIMEI(mContext),"",Constant.ANDROID_OS,Utils.getDeviceName(),Utils.getDeviceVersion(),"0","0");
        if(login.getFirst_name()!=null&&login.getLast_name()!=null){
            Snackbar.make(mLoginLayout,"Welcome "+login.getFirst_name()+" "+login.getLast_name(),Snackbar.LENGTH_LONG).show();
        }
        replaceFagment(getFragmentManager(),R.id.fragment, MainFragment.newInstance());
    }

    @Override
    public void login_error() {
        Snackbar.make(mLoginLayout,"Login Fail!",Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.custom_signup_button)
    void signUp(){
        replaceFagment(getFragmentManager(),R.id.fragment,RegisterFragment.newInstance());
    }

    @OnClick(R.id.branch)
    void branch(){
      if(branchList!=null&&branchList.size()>0) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(mContext);
        View mView = layoutInflaterAndroid.inflate(R.layout.choose_branch_dialog, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mContext);
        alertDialogBuilderUserInput.setView(mView);
        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
        RecyclerView recycler_branch = (RecyclerView) mView.findViewById(R.id.recycler_branch);
        recycler_branch.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_branch.setHasFixedSize(true);
        recycler_branch.setAdapter(new BranchAdapter(mContext,this,branchList,alertDialogAndroid));
        alertDialogBuilderUserInput.setCancelable(false);
      }else{
        Snackbar.make(mLoginLayout,"Get Branch Error",Snackbar.LENGTH_LONG).show();
      }
    }
    @OnClick(R.id.login_fb_button)
    void singUpFace(){
        Snackbar.make(mLoginLayout,".......",Snackbar.LENGTH_LONG).show();
    }
    @OnClick(R.id.login_google_button)
    void login_google_button(){
        Snackbar.make(mLoginLayout,".......",Snackbar.LENGTH_LONG).show();
    }

  @Override
  public void clickItem(final String newShopID,String address) {
    Constant.SHOP_ID=newShopID;
    mBranch.setText(address);
  }
}

