package com.example.evv.mtsfarm.repo.local;

import android.util.Log;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.repo.local.dao.CowDao;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class AppLocalRepo implements FarmRepository {

    private CowDao dao = App.getDatabase().cowDao();
    private final String TAG = this.getClass().getSimpleName();

    public Observable<List<Cow>> getCows() {
        return App.getDatabase().cowDao().getCows()
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

    public Observable<Storage> addData(Storage storage) {
        return Observable.fromCallable(new Callable<Storage>() {
                                           @Override
                                           public Storage call() throws Exception {
                                               dao.addCows(storage.getCows());
                                               App.getDatabase().milkingDao().addMilking(storage.getMilkings());
                                               return null;
                                           }
                                       }


        );
    }

}
