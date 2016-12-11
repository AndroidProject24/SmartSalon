package com.techart.smartsalon.ui.fragment.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.techart.smartsalon.R;
import com.techart.smartsalon.libs.loading.AVLoadingIndicatorView;
import com.techart.smartsalon.mvp.model.profile.Profile;
import com.techart.smartsalon.mvp.presenter.login.UpdateProfilePresenter;
import com.techart.smartsalon.mvp.view.login.UpdateProflieView;
import com.techart.smartsalon.ui.activity.MainActivity;
import com.techart.smartsalon.ui.fragment.BaseFragment;
import com.techart.smartsalon.ui.fragment.MainFragment;
import com.techart.smartsalon.utils.Constant;
import com.techart.smartsalon.utils.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Toan.IT
 * Date: 11/06/2016
 */

public class UpdateProfileFragment extends BaseFragment implements UpdateProflieView {
    @BindView(R.id.img_avatar)
    ImageView mImgAvatar;
    @BindView(R.id.txt_fistName)
    EditText mTxtFistName;
    @BindView(R.id.txt_lastName)
    EditText mTxtLastName;
    @BindView(R.id.txt_phone)
    EditText mTxtPhone;
    @BindView(R.id.txt_address)
    EditText mTxtAddress;
    @BindView(R.id.txt_postcode)
    EditText mTxtPostcode;
    @BindView(R.id.avloadingIndicatorView)
    AVLoadingIndicatorView mAvloadingIndicatorView;
    private Context mContext;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int GALLERY_CAPTURE_IMAGE_REQUEST_CODE = 200;
    private Bitmap mBitmap;
    @Inject
    UpdateProfilePresenter
    mUpdateProfilePresenter;

    public static Fragment newInstance(boolean isFist) {
        UpdateProfileFragment updateProfileFragment=new UpdateProfileFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean(Constant.BundleIsFist,isFist);
        updateProfileFragment.setArguments(bundle);
        return updateProfileFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.profile_fragment;
    }

    @Override
    protected void initData() {
        try {
            if (!getArguments().getBoolean(Constant.BundleIsFist)) {
                mTxtFistName.setText(mUpdateProfilePresenter.getPreferencesHelper().getFistName());
               // mTxtLastName.setText(mUpdateProfilePresenter.getPreferencesHelper().getLastName());
                mTxtLastName.setText(mUpdateProfilePresenter.getPreferencesHelper().getEmail());
                mTxtPhone.setText(mUpdateProfilePresenter.getPreferencesHelper().getPhone());
                mTxtAddress.setText(mUpdateProfilePresenter.getPreferencesHelper().getAddress());
                mTxtPostcode.setText(mUpdateProfilePresenter.getPreferencesHelper().getPostcode());
          /*  Glide.with(mContext)
                    .load(Base64.decode(mUpdateProfilePresenter.getPreferencesHelper().getAvatar(), Base64.DEFAULT))
                    .crossFade()
                    .into(mImgAvatar);*/
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void initViews() {
        ((MainActivity) getActivity()).getActivityComponent().inject(this);
        mUpdateProfilePresenter.attachView(this);
    }

    @Override
    public void updateProfile(Profile profile) {
        new Thread(() -> {
            mUpdateProfilePresenter.getPreferencesHelper().putFistName(profile.getFirst_name());
            mUpdateProfilePresenter.getPreferencesHelper().putLastName(profile.getLast_name());
            mUpdateProfilePresenter.getPreferencesHelper().putEmail(profile.getEmail());
            mUpdateProfilePresenter.getPreferencesHelper().putAddress(profile.getAddress());
            mUpdateProfilePresenter.getPreferencesHelper().putAvatar(profile.getAvatar());
            mUpdateProfilePresenter.getPreferencesHelper().putPhone(profile.getPhone());
            mUpdateProfilePresenter.getPreferencesHelper().putPostcode(profile.getPostcode());
        });
        replaceFagment(getFragmentManager(), R.id.fragment, MainFragment.newInstance());
    }

    @Override
    public void getProfile(Profile profile) {

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

    }

    @Override
    public void showEmty() {

    }

    @OnClick(R.id.img_avatar)
    void img_avatar(){
        Dialog_Update_avatar();
    }
    @OnClick(R.id.btn_update)
    void btn_update() {
        mUpdateProfilePresenter.updateProfile(mTxtFistName.getText().toString(), mTxtLastName.getText().toString(), mTxtPhone.getText().toString(), mTxtAddress.getText().toString(), mTxtPostcode.getText().toString(),mBitmap);
    }

    @OnClick(R.id.btn_not_now)
    void btn_not_now() {
        replaceFagment(getFragmentManager(), R.id.fragment, MainFragment.newInstance());
    }
    private void Dialog_Update_avatar() {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_avatar);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Button camera = (Button) dialog.findViewById(R.id.camera);
        Button thuvien = (Button) dialog.findViewById(R.id.thuvien);
        Button huy = (Button) dialog.findViewById(R.id.huy);
        camera.setOnClickListener(v -> {
            try{
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Logger.e(intent.toString());
                startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                dialog.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        thuvien.setOnClickListener(v -> {
            try{
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                Logger.e(intent.toString());
                startActivityForResult(Intent.createChooser(intent, "Select File"), GALLERY_CAPTURE_IMAGE_REQUEST_CODE);
                dialog.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        huy.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(data!=null) {
                if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
                    try {
                        Logger.e(data.getExtras().get("data").toString());
                        mBitmap = (Bitmap) data.getExtras().get("data");
                        mImgAvatar.setImageBitmap(mBitmap);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                } else if (requestCode == GALLERY_CAPTURE_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
                    try {
                        Uri selectedImageUri = data.getData();
                        Logger.e(selectedImageUri.toString());
                        String[] fileColumn = { MediaStore.Images.Media.DATA };
                        Cursor imageCursor = mContext.getContentResolver().query(selectedImageUri,
                                fileColumn, null, null, null);
                        imageCursor.moveToFirst();
                        int fileColumnIndex = imageCursor.getColumnIndex(fileColumn[0]);
                        mBitmap=BitmapFactory.decodeFile(imageCursor.getString(fileColumnIndex));
                        mImgAvatar.setImageBitmap(mBitmap);
                    } catch (NullPointerException e) {
                        Log.e(e.getClass().getName(), e.getMessage(), e);
                    }
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
