package com.example.evv.mtsfarm.ui.detail;

import com.example.evv.mtsfarm.data.Detail;
import com.example.evv.mtsfarm.ui.BasePresenter;
import com.example.evv.mtsfarm.ui.BaseView;

public interface ContractDetail {
    interface View extends BaseView {
        void setData(Detail detailList);
    }

    interface Presenter extends BasePresenter {
        void getData();

        View getView();
    }
}
