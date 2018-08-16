package com.example.evv.mtsfarm.ui.main;

import android.util.Log;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.R;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.ui.AppBasePresenter;
import com.example.evv.mtsfarm.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;
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
                        .doOnSubscribe(data -> getView().showLoading())
                        .subscribe(
                                data -> {
                                    getView().hideLoading();
                                    getView().refreshData(data);
                                }, throwable -> handleError(throwable, R.string.err_data_load)));
    }

    @Override
    public ContractMain.View getView() {
        ContractMain.View view = (ContractMain.View) getBaseView();
        if (view == null) {
            Log.e(TAG, "Fake View");
            return new ContractMain.View() {
                @Override
                public void showError(int resId) {

                }

                @Override
                public void showToast(int resId) {

                }

                @Override
                public void showLoading() {

                }

                @Override
                public void hideLoading() {

                }

                @Override
                public void showInternet() {

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
