package com.techart.smartsalon.mvp.view.loyalty;

import com.techart.smartsalon.mvp.model.appointment.PrepaidPackages;
import com.techart.smartsalon.mvp.model.loyalty.Loyalty;
import com.techart.smartsalon.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface LoyaltyView extends BaseView {
    void getLoyalty(Loyalty loyalty);

    void getPrepaidPackages(List<PrepaidPackages> prepaidPackagesList);
}
