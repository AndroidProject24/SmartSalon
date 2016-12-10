package com.techart.smartsalon.mvp.view.loyalty;

import com.techart.smartsalon.mvp.model.loyalty.PrepaidAppointment;
import com.techart.smartsalon.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface LoyaltyDetailsView extends BaseView {
    void getLoyaltyDetails(List<PrepaidAppointment> prepaidAppointmentList);
}
