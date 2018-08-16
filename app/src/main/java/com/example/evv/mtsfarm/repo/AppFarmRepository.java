package com.example.evv.mtsfarm.repo;

import android.support.annotation.NonNull;

import com.example.evv.mtsfarm.data.Cow;
import com.example.evv.mtsfarm.data.Storage;
import com.example.evv.mtsfarm.repo.local.AppLocalRepo;
import com.example.evv.mtsfarm.repo.remote.AppRepoRemote;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


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
    public Observable<List<Cow>> getCows() {
        return mLocal.getCows()
                .flatMap(data -> {
                    if (data.size() == 0)
                        return mRemote.getCows()
                                .flatMap(list -> mLocal.addCows(list));
                    return Observable.just(data);
                });
    }

    @Override
    public Observable clearDb() {
        return mLocal.clearDb();
    }

}
