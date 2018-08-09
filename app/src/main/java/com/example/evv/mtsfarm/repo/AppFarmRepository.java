package com.example.evv.mtsfarm.repo;

import android.support.annotation.NonNull;

import com.example.evv.mtsfarm.data.Cow;

import java.util.List;

import io.reactivex.Single;


public class AppFarmRepository implements FarmRepository {

    private static AppFarmRepository INSTANCE = null;
    private FarmRepository mLocalRepo;
    private FarmRepository mRemoteRepo;

    private AppFarmRepository(@NonNull FarmRepository local, @NonNull FarmRepository remote) {
        mLocalRepo = local;
        mRemoteRepo = remote;
    }

    @Override
    public Single<List<Cow>> getCows() {
        return null;
    }

    public static FarmRepository getInstance(@NonNull FarmRepository local, @NonNull FarmRepository remote) {
        if (INSTANCE == null)
            return INSTANCE = new AppFarmRepository(local, remote);
        return INSTANCE;
    }
}
