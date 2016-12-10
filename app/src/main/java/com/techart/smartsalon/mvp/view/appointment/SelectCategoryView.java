package com.techart.smartsalon.mvp.view.appointment;

import com.techart.smartsalon.mvp.model.appointment.ListCategory;
import com.techart.smartsalon.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 10/12/2016
 */
public interface SelectCategoryView extends BaseView {
    void selectCategory(List<ListCategory> listCategory);
}
