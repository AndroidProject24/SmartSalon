package com.techart.smartsalon.mvp.view.appointment;

import com.techart.smartsalon.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface SlotAppointmentView extends BaseView {
    void selectSlot(List<String> stringList);
}
