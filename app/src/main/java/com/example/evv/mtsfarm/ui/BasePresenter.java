package com.example.evv.mtsfarm.ui;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void detouchView(V view);

    void destroy();
}
