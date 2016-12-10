package com.techart.smartsalon.mvp.model.appointment;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 14/06/2016
 */

public class CashOnDeliveryList{
    private String category_id;

    @Override
    public String toString() {
        return "CashOnDeliveryList{" +
                "category_id='" + category_id + '\'' +
                ", category_name='" + category_name + '\'' +
                ", _0=" + _0 +
                '}';
    }

    private String category_name;
    private List<CashOnDelivery> _0;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<CashOnDelivery> getServices() {
        return _0;
    }

    public void setServices(List<CashOnDelivery> services) {
        this._0 = services;
    }
}
