package com.techart.smartsalon.mvp.view.login;

import com.techart.smartsalon.mvp.model.profile.Profile;
import com.techart.smartsalon.mvp.view.base.BaseView;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface UpdateProflieView extends BaseView {
    void updateProfile(Profile profile);

    void getProfile(Profile profile);
}
