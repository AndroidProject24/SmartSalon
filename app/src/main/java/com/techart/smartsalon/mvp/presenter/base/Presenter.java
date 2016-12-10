package com.techart.smartsalon.mvp.presenter.base;

import com.techart.smartsalon.mvp.view.base.BaseView;

/**
 * Every presenter in the app must either implement this interface or extend Presenter
 * indicating the BaseView type that wants to be attached with.
 */
public interface Presenter<V extends BaseView> {

    void attachView(V mvpView);

    void detachView();
}