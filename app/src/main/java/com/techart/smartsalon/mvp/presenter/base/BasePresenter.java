package com.techart.smartsalon.mvp.presenter.base;

import com.techart.smartsalon.mvp.view.base.BaseView;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {
    private T mMvpView;
    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }
    @Override
    public void detachView() {
        mMvpView = null;
    }

    private boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        private MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(BaseView) before" + "requesting data to the Presenter");
        }
    }
}

