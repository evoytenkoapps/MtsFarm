package com.example.evv.mtsfarm.repo;

import android.support.annotation.NonNull;

import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.repo.local.AppLocalRepo;
import com.example.evv.mtsfarm.repo.remote.AppRepoRemote;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.internal.operators.observable.ObservableElementAt;


public class AppFarmRepository implements FarmRepository {

    private static AppFarmRepository INSTANCE = null;
    private FarmRepository mLocal;
    private FarmRepository mRemote;

    private AppFarmRepository(@NonNull FarmRepository local, @NonNull FarmRepository remote) {
        mLocal = local;
        mRemote = remote;
    }

    @Override
    public Single<Storage> getData() {

        return Single.concat(mLocal.getData(), mRemote.getData());

    }

    public static FarmRepository getInstance(@NonNull AppLocalRepo local, @NonNull AppRepoRemote remote) {
        if (INSTANCE == null)
            return INSTANCE = new AppFarmRepository(local, remote);
        return INSTANCE;
    }
}
