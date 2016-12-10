package com.techart.smartsalon.mvp.view.appointment;

import com.techart.smartsalon.mvp.model.appointment.CashOnDelivery;
import com.techart.smartsalon.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface CashAppointmentView extends BaseView {
    void cashOnDelivery(List<CashOnDelivery> cashOnDeliveryList);
}
