package com.example.evv.mtsfarm.ui;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class AppBasePresenter implements BasePresenter {
    private BaseView mView = null;
    private CompositeDisposable mDisp = new CompositeDisposable();

    @Override
    public void attachView(BaseView view) {
        mView = view;
    }

    @Override
    public void detouchView() {
        mView = null;
    }

    @Override
    public void destroy() {
        mDisp.dispose();
    }

    @Override
    public void addToDisp(Disposable disposable) {
        mDisp.add(disposable);
    }

    @Override
    public BaseView getBaseView() {
        return mView;
    }

    @Override
    public void handleError(Throwable throwable, int resId) {
        throwable.printStackTrace();
        if (mView != null)
            mView.showError(resId);
    }

}
