package com.example.evv.mtsfarm.ui.main;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.R;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.ui.AppBasePresenter;
import com.example.evv.mtsfarm.ui.BaseView;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterMain extends AppBasePresenter implements ContractMain.Presenter {

    private final String TAG = this.getClass().getSimpleName();
    private FarmRepository mRepo = App.getRepo();
    private ArrayDeque<String> mQue = new ArrayDeque<>();
    private final String SHOW_LOADING = "SHOW_LOADING";
    private final String HIDE_LOADING = "HIDE_LOADING";
    private final String REFRESH_DATA = "REFRESH_DATA";

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
                        .doFinally(() -> getView().hideLoading())
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
                public void showToast(int resId) {

                }

                @Override
                public void showLoading() {
                    mQue.addFirst(SHOW_LOADING);
                }

                @Override
                public void hideLoading() {
                    mQue.addFirst(HIDE_LOADING);
                }

                @Override
                public void refreshData(List<Cow> data) {
                    mQue.addFirst(REFRESH_DATA);
                }
            };
        }
        return view;
    }

    @Override
    public void setId(int id) {
        mRepo.setId(id);
    }

    @Override
    public void attachView(BaseView view) {
        super.attachView(view);
        String state;
        while ((state = mQue.pollLast()) != null) {
            switch (state) {
                case SHOW_LOADING:
                    ((ContractMain.View) view).showLoading();
                    break;
                case HIDE_LOADING:
                    ((ContractMain.View) view).hideLoading();
                    break;
                case REFRESH_DATA:
                    getData();
                    break;
            }
        }
    }
}
