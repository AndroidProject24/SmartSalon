package com.techart.smartsalon.ui.fragment.contact;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.techart.smartsalon.R;
import com.techart.smartsalon.interfaces.ClickItemListener;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.contact.Contact;
import com.techart.smartsalon.mvp.model.contact.Freedback;
import com.techart.smartsalon.mvp.presenter.contact.ContactPresenter;
import com.techart.smartsalon.mvp.view.contact.ContactView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.adapter.ContactAdapter;
import com.techart.smartsalon.ui.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class ContactFragment extends BaseFragment implements ContactView,ClickItemListener {
    @Inject
    ContactPresenter
    mContactPresenter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    public static Fragment newInstance() {
        return new ContactFragment();
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.contact_fragment;
    }

    @Override
    protected void initData() {
        mContactPresenter.getContactList();
    }

    @Override
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mContactPresenter.attachView(this);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setHasFixedSize(true);
    }

    @Override
    public void getContactList(List<Contact> contactList) {
        ContactAdapter contactAdapter=new ContactAdapter(mContext,this,contactList);
        mRecyclerview.setAdapter(contactAdapter);
    }

    @Override
    public void sendFreedback(Freedback freedback) {
        mContactPresenter.getContactList();
        Snackbar.make(mRecyclerview,"Send Success",Snackbar.LENGTH_LONG).show();
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
        Snackbar.make(mRecyclerview,"Error",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showEmty() {

    }

    @Override
    public void clickItem(String subject, String content) {

    }
    @OnClick(R.id.btnSend_Feedback)
    void btnSend_Feedback(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(mContext);
        View mView = layoutInflaterAndroid.inflate(R.layout.send_contact_dialog, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(mContext);
        alertDialogBuilderUserInput.setView(mView);
        final EditText edit_subject = (EditText) mView.findViewById(R.id.edit_Subject);
        final EditText edit_content = (EditText) mView.findViewById(R.id.edit_content);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Send", (dialogBox, id) -> mContactPresenter.sendFreedback(edit_subject.getText().toString(),edit_content.getText().toString()))

                .setNegativeButton("Cancel",
                        (dialogBox, id) -> dialogBox.cancel());

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }
}