package com.example.evv.mtsfarm.ui;

import android.util.Log;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.repo.FarmRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppPresenter implements ContractMain.Presenter {
    private final String TAG = this.getClass().getSimpleName();
    private ContractMain.View mView = null;
    private FarmRepository mRepo = App.getRepo();

    @Override
    public void attachView(BaseView view) {
        mView = (ContractMain.View) view;
    }

    @Override
    public void detouchView(BaseView view) {
        mView = null;
    }

    @Override
    public void destroy() {

    }


    @Override
    public void init() {
        List<Cow> result = new ArrayList<>();
        mRepo.getCows()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> Log.d(TAG, "Done"), throwable -> {
                });
    }
}
