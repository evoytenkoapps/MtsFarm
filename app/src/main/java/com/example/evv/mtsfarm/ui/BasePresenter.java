package com.example.evv.mtsfarm.ui;

import io.reactivex.disposables.Disposable;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void detouchView(V view);

    void destroy();

    void addToDisp(Disposable disposable);

    V getBaseView();
}
