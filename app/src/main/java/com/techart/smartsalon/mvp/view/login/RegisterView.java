package com.techart.smartsalon.mvp.view.login;

import com.techart.smartsalon.mvp.model.register.Register;
import com.techart.smartsalon.mvp.view.base.BaseView;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface RegisterView extends BaseView {
    void register(Register register);

    void checkEmail();

}
