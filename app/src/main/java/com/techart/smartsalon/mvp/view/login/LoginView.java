package com.techart.smartsalon.mvp.view.login;

import com.techart.smartsalon.mvp.model.login.Branch;
import com.techart.smartsalon.mvp.model.login.Login;
import com.techart.smartsalon.mvp.view.base.BaseView;
import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface LoginView extends BaseView {

    void getBranch(List<Branch> branchList);

    void login(Login login);

    void login_error();
}
