package com.techart.smartsalon.mvp.view.myappointment;

import com.techart.smartsalon.mvp.model.myappointment.ListAppointment;
import com.techart.smartsalon.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface MyAppointmentView extends BaseView {
    void myAppointmentList(List<ListAppointment> listAppointments);
}
