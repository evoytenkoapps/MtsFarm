package com.example.evv.mtsfarm.repo.local;

import android.arch.persistence.room.Dao;
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

    private AppDao dao = App.getDatabase().appDao();
    private final String TAG = this.getClass().getSimpleName();

    public Observable<List<Cow>> getCows() {
        return App.getDatabase().appDao().getCows()
                .toObservable();
    }

    @Override
    public Observable<Void> clearDb() {
        return Observable.fromCallable(new Callable<Void>() {
                                           @Override
                                           public Void call() throws Exception {
                                               Log.d(TAG, "delete cows");
                                               dao.deleteCows();
                                               return null;
                                           }
                                       }


//                () -> {
//            Log.d(TAG, "delete cows");
//            dao.deleteCows();
//            return null;
//        }
//
        );
    }

    public Observable<List<Cow>> addCows(List<Cow> cows) {
        return Observable.fromCallable(() -> {
            dao.insert(cows);
            return cows;
        });
    }

}
