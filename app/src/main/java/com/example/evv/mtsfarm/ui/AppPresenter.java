package com.example.evv.mtsfarm.ui;

import android.util.Log;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.repo.FarmRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AppPresenter implements ContractMain.Presenter {
    private final String TAG = this.getClass().getSimpleName();
    private ContractMain.View mView = null;
    private FarmRepository mRepo = App.getRepo();
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

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
        mCompositeDisposable.dispose();
    }


    @Override
    public void init() {
        mCompositeDisposable.add(
                mRepo.getCows()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                data -> {
                                    Log.d(TAG, "Result " + data.toString());
                                }, Throwable::printStackTrace));
    }
}
