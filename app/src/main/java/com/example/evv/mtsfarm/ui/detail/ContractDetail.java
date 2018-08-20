package com.example.evv.mtsfarm.ui.detail;

import com.example.evv.mtsfarm.data.Detail;

public interface ContractDetail {
    interface View {
        void setData(Detail detailList);
    }

    interface Presenter {
        void getData();

        View getView();
    }
}
