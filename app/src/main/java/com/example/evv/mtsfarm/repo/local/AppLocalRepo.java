package com.example.evv.mtsfarm.repo.local;

import android.util.Log;

import com.example.evv.mtsfarm.App;
import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Detail;
import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.repo.FarmRepository;
import com.example.evv.mtsfarm.repo.local.dao.CowDao;
import com.example.evv.mtsfarm.repo.local.dao.MilkingDao;
import com.example.evv.mtsfarm.repo.local.dao.TemperatureDao;
import com.example.evv.mtsfarm.repo.local.dao.WeightDao;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AppLocalRepo implements FarmRepository {

    private CowDao cowDao = App.getDatabase().cowDao();
    private MilkingDao milkingDao = App.getDatabase().milkingDao();
    private TemperatureDao temperatureDao = App.getDatabase().temperatureDao();
    private WeightDao weightDao = App.getDatabase().weightDao();

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
                                               cowDao.deleteCows();
                                               return null;
                                           }
                                       }
        );
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public Single<Detail> getDetail() {
        return null;
    }

    public Single<Detail> getDetail(int id) {
        return Single.fromCallable(new Callable<Detail>() {
                                       @Override
                                       public Detail call() throws Exception {
                                           Detail result = new Detail();
                                           result.setMilkings(milkingDao.getMilking(id));
                                           result.setWeights(weightDao.getWeight(id));
                                           result.setTemperatures(temperatureDao.getTemperature(id));
                                           return result;
                                       }
                                   }
        );
    }

    public Observable<Storage> addData(Storage storage) {
        return Observable.fromCallable(new Callable<Storage>() {
                                           @Override
                                           public Storage call() throws Exception {
                                               cowDao.addCows(storage.getCows());
                                               milkingDao.addMilking(storage.getMilkings());
                                               weightDao.addWeight(storage.getWeights());
                                               temperatureDao.addTemperature(storage.getTemperatures());
                                               return storage;
                                           }
                                       }
        );
    }

}
