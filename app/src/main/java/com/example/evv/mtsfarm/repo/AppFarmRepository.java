package com.example.evv.mtsfarm.repo;

import android.support.annotation.NonNull;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Detail;
import com.example.evv.mtsfarm.data.Milking;
import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.data.Temperature;
import com.example.evv.mtsfarm.data.Weight;
import com.example.evv.mtsfarm.repo.local.AppLocalRepo;
import com.example.evv.mtsfarm.repo.remote.AppRepoRemote;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;


public class AppFarmRepository implements FarmRepository {

    private static AppFarmRepository INSTANCE = null;
    private AppLocalRepo mLocal;
    private AppRepoRemote mRemote;
    private int mId;

    private AppFarmRepository(@NonNull AppLocalRepo local, @NonNull AppRepoRemote remote) {
        mLocal = local;
        mRemote = remote;
    }


    public static FarmRepository getInstance(@NonNull AppLocalRepo local, @NonNull AppRepoRemote remote) {
        if (INSTANCE == null)
            return INSTANCE = new AppFarmRepository(local, remote);
        return INSTANCE;
    }

    @Override
    public Observable<List<Cow>> getCows() {
        return mLocal.getCows()
                .flatMap(data -> {
                    if (data.size() == 0)
                        return mRemote.getData()
                                .flatMap(list -> mLocal.addData(list))
                                .map(Storage::getCows);
                    return Observable.just(data);
                });
    }

    @Override
    public Single<Cow> getCow() {
        return mLocal.getCow(mId);
    }

    @Override
    public Observable<Void> clearDb() {
        return mLocal.clearDb();
    }

    @Override
    public void setId(int id) {
        mId = id;
    }


    @Override
    public Single<Detail> getDetail() {
        return mLocal.getDetail(mId);
    }

    @Override
    public Completable updateMilking(List<Milking> milkings) {
        return mLocal.updateMilking(milkings);
    }

    @Override
    public Completable updateWeight(List<Weight> weights) {
        return mLocal.updateWeight(weights);
    }

    @Override
    public Completable updateTemperature(List<Temperature> temperatures) {
        return mLocal.updateTemperature(temperatures);
    }

}
