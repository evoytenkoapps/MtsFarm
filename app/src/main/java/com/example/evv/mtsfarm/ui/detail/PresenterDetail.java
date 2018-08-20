package com.example.evv.mtsfarm.ui.detail;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.R;
import com.example.evv.mtsfarm.data.Detail;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.ui.AppBasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PresenterDetail extends AppBasePresenter implements ContractDetail.Presenter {
    private FarmRepository mRepo = App.getRepo();


    @Override
    public void getData() {
        addToDisp(
                mRepo.getDetail()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> getView().setData(data), throwable -> handleError(throwable, R.string.error_load_detail))
        );
    }

    @Override
    public ContractDetail.View getView() {
        ContractDetail.View view = (ContractDetail.View) super.getBaseView();
        if (view == null) {
            return new ContractDetail.View() {
                @Override
                public void setData(Detail detailList) {

                }
            };
        } else {
            return view;
        }
    }


}
