package com.example.evv.mtsfarm.ui.main;


import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.ui.BasePresenter;
import com.example.evv.mtsfarm.ui.BaseView;

import java.util.List;

public interface ContractMain {
    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showInternet();

        void getData();

        void refreshData(List<Cow> data);
    }

    interface Presenter extends BasePresenter {
        void getData();

        void destroy();

        View getView();
    }
}
