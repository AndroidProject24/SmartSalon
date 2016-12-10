package com.techart.smartsalon.mvp.view.offers;

import com.techart.smartsalon.mvp.model.offers.Offers;
import com.techart.smartsalon.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface OffersView extends BaseView {
    void getOffers(List<Offers> offersList);
}
