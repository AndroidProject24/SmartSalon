package com.techart.smartsalon.mvp.model;

import com.google.gson.annotations.Expose;

/**
 * Created by toan.it
 * Date: 24/05/2016
 */
public class JsonObject<T> {
    @Expose
    public T payload;
}
