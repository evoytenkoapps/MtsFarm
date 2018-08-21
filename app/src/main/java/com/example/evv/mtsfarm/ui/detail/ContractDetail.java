package com.example.evv.mtsfarm.ui.detail;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Detail;
import com.example.evv.mtsfarm.data.Milking;
import com.example.evv.mtsfarm.data.Temperature;
import com.example.evv.mtsfarm.data.Weight;
import com.example.evv.mtsfarm.ui.BasePresenter;
import com.example.evv.mtsfarm.ui.BaseView;

import java.util.List;

public interface ContractDetail {
    interface View extends BaseView {
        void setData(Detail detailList);

        void setCow(Cow cow);
    }

    interface Presenter extends BasePresenter {
        void getData();

        View getView();

        void saveMilking(List<Milking> milkings);

        void saveWeight(List<Weight> weights);

        void saveTemperature(List<Temperature> temperatures);

        void getCow();

    }
}
