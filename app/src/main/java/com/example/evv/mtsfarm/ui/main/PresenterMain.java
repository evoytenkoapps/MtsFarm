package com.example.evv.mtsfarm.ui.main;

import android.util.Log;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.ui.AppBasePresenter;
import com.example.evv.mtsfarm.ui.BaseView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterMain extends AppBasePresenter implements ContractMain.Presenter {
    private final String TAG = this.getClass().getSimpleName();
    private FarmRepository mRepo = App.getRepo();


    @Override
    public void getData() {
//        mRepo.clearDb()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(v -> {
//                }, throwable -> throwable.printStackTrace());


        addToDisp(
                mRepo.getCows()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                data -> {
                                    Log.d(TAG, "Result " + data.toString());
                                    getView().refreshData(data);
                                }, Throwable::printStackTrace));
    }

    @Override
    public ContractMain.View getView() {
        ContractMain.View view = (ContractMain.View) getBaseView();
        if (view == null) {
            return new ContractMain.View() {
                @Override
                public void showLoading() {

                }

                @Override
                public void showInternet() {

                }

                @Override
                public void showToast() {

                }

                @Override
                public void getData() {

                }

                @Override
                public void refreshData(List<Cow> data) {

                }
            };
        }
        return view;
    }


}
