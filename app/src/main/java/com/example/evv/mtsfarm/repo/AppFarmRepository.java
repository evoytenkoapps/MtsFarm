package com.example.evv.mtsfarm.repo;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.repo.local.AppDataBase;
import com.example.evv.mtsfarm.repo.local.AppLocalRepo;
import com.example.evv.mtsfarm.repo.remote.AppRepoRemote;

import java.util.Collection;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.internal.operators.observable.ObservableBuffer;
import io.reactivex.internal.operators.observable.ObservableElementAt;


public class AppFarmRepository implements FarmRepository {

    private static AppFarmRepository INSTANCE = null;
    private AppLocalRepo mLocal;
    private AppRepoRemote mRemote;

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
    public Observable<Storage> getData() {
        return Observable.concat(mLocal.getData(), mRemote.getData())
                .first(new Storage())
                .doOnSuccess(storage -> mLocal.addCows(storage.getCows()))
                .toObservable();
    }


//    private Observable<Storage> getRemote() {
//        return mRemote.getData()
//                .flatMap(storage -> mLocal.addCows(storage.getCows()))
//    }


}
