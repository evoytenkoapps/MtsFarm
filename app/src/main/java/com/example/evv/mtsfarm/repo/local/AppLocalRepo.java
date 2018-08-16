package com.example.evv.mtsfarm.repo.local;

import android.util.Log;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.repo.FarmRepository;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class AppLocalRepo implements FarmRepository {

    @Override
    public Observable<List<Cow>> getCows() {
        return App.getDatabase().appDao().getCows()
                .toObservable();
    }

//    public void addCows(List<Cow> cows) {
//        mDb.appDao().insert(cows);
//
//    }

    public Observable<List<Cow>> addCows(List<Cow> cows) {
        return Observable.fromCallable(() -> {
            App.getDatabase().appDao().insert(cows);
            return cows;
        });
    }


}
